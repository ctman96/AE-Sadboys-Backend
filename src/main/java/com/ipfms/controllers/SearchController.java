package com.ipfms.controllers;

import com.ipfms.domain.model.Classification;
import com.ipfms.domain.model.Container;
import com.ipfms.domain.model.Record;
import com.ipfms.domain.model.SearchResult;
import com.ipfms.domain.repository.ContainerRepository;
import com.ipfms.domain.repository.RecordRepository;
import com.ipfms.exception.EntityNotFoundException;
import org.hibernate.CacheMode;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.hateoas.PagedResources;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/search")
public class SearchController {
    private final EntityManagerFactory entityManagerFactory;

    @Autowired
    public SearchController(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    @RequestMapping()
    public ResponseEntity<PagedResources<SearchResult>> search(
            //Search Query
            @RequestParam(value="query", required=true) String query,

            //Pagination settings
            @RequestParam(value = "pageSize", required = false) Integer size,
            @RequestParam(value = "page", required = false) Integer page,

            //Whether to include records/containers
            @RequestParam(value="records", required = false) Boolean includeRecords,
            @RequestParam(value="containers", required= false) Boolean includeContainers,

            //Filters
            @RequestParam(value="classification", required=false) String classification,
            @RequestParam(value="created", required=false) @DateTimeFormat(pattern="yyyy-MM-dd") Date createdAt,
            @RequestParam(value="updated", required=false) @DateTimeFormat(pattern="yyyy-MM-dd") Date  updatedAt,
            @RequestParam(value="closed", required=false) @DateTimeFormat(pattern="yyyy-MM-dd") Date  closedAt,
            @RequestParam(value="location", required=false) String location,
            @RequestParam(value="schedule", required=false) String schedule,
            @RequestParam(value="state", required=false) String state,
            @RequestParam(value="type", required=false) String type){

        System.out.println("In 'search'");

        //Check for null parameters, set to default values
        if(size == null){
            size = 10;
        }
        if(page == null){
            page = 0;
        }
        if(includeRecords == null){
            includeRecords = true;
        }
        if(includeContainers == null){
            includeContainers = true;
        }

        List<SearchResult> results = new ArrayList<>();


        EntityManager em = entityManagerFactory.createEntityManager();
        FullTextEntityManager fullTextEntityManager =
                org.hibernate.search.jpa.Search.getFullTextEntityManager(em);
        em.getTransaction().begin();

        //Build and execute Record Query
        if(includeRecords) {
            QueryBuilder qbr = fullTextEntityManager.getSearchFactory()
                    .buildQueryBuilder().forEntity(Record.class).get();

            org.apache.lucene.search.Query luceneQueryR = qbr
                    .keyword()
                    .onFields("number", "title", "consignmentCode")
                    .matching(query)
                    .createQuery();

            javax.persistence.Query jpaQueryR =
                    fullTextEntityManager.createFullTextQuery(luceneQueryR, Record.class);

            List<Record> resultR = jpaQueryR.getResultList();

            //Filter and add to full results
            for (Record r : resultR) {
                Boolean doAdd = true;
                doAdd = doAdd && ((classification == null) ? true : checkClassifications(r, classification));
                doAdd = doAdd && ((createdAt == null) || compareDates(r.getCreatedAt(), createdAt));
                doAdd = doAdd && ((updatedAt == null) || compareDates(r.getUpdatedAt(), updatedAt));
                doAdd = doAdd && ((closedAt == null) || compareDates(r.getClosedAt(), closedAt));
                doAdd = doAdd && ((location == null) || (
                        r.getLocation().getName().equals(location) || r.getLocation().getName().equals(location)));
                doAdd = doAdd && ((schedule == null)
                        || ( r.getSchedule().getName().equals(schedule) || r.getSchedule().getCode().equals(schedule)));
                doAdd = doAdd && ((state == null) || (r.getState().getName().equals(state)));
                doAdd = doAdd && ((type == null) || (r.getType().getName().equals(type)));

                if (doAdd) {
                    results.add(new SearchResult(r));
                }
            }
        }

        //Build and execute Container Query
        if(includeContainers) {
            QueryBuilder qbc = fullTextEntityManager.getSearchFactory()
                    .buildQueryBuilder().forEntity(Container.class).get();


            org.apache.lucene.search.Query luceneQueryC = qbc
                    .keyword()
                    .onFields("number", "title", "consignmentCode")
                    .matching(query)
                    .createQuery();

            javax.persistence.Query jpaQueryC =
                    fullTextEntityManager.createFullTextQuery(luceneQueryC, Container.class);
            List<Container> resultC = jpaQueryC.getResultList();

            for(Container c : resultC){
                Boolean doAdd = true;
                doAdd = doAdd && ((createdAt == null) || compareDates(c.getCreatedAt(), createdAt));
                doAdd = doAdd && ((updatedAt == null) ||compareDates(c.getUpdatedAt(), updatedAt));
                //TODO: Do containers get filtered by anything else, depending on subrecords?
                if(doAdd) {
                    results.add(new SearchResult(c));
                }
            }
        }

        Pageable pageable = new PageRequest(page, size);

        int start = pageable.getOffset();
        int end = (start + pageable.getPageSize()) > results.size() ? results.size() : (start + pageable.getPageSize());
        Page<SearchResult> pageResult = new PageImpl<>(results.subList(start, end), pageable, results.size());

        PagedResources.PageMetadata metadata = new PagedResources.PageMetadata(
                pageResult.getSize(), pageResult.getNumber(),
                pageResult.getTotalElements(), pageResult.getTotalPages());

        PagedResources<SearchResult> resources = new PagedResources<SearchResult>(pageResult.getContent(), metadata);
        System.out.println("Exiting 'search'");
        return ResponseEntity.ok(resources);
    }

    //Classification Filter Helper
    private Boolean checkClassifications(Record record, String classificationVal){

                if (record.getClassifications().isEmpty()){
                    return false;
                }

                Boolean check = true;
                for (Classification c : record.getClassifications()){
                    check = check && c.getName().equals(classificationVal);
                }
                return check;
    }

    //Date Comparison Helper
    private Boolean compareDates(Date date1, Date date2){
        SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMdd");
        return fmt.format(date1).equals(fmt.format(date2));
    }

    @RequestMapping("/reload-index")
    public void reload(){
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        FullTextEntityManager fullTextEntityManager =
                Search.getFullTextEntityManager(entityManager);
        try {
            fullTextEntityManager
                    .createIndexer(Record.class, Container.class)
                    .typesToIndexInParallel( 2 )
                    .batchSizeToLoadObjects( 25 )
                    .threadsToLoadObjects( 12 )
                    .idFetchSize( 150 )
                    .startAndWait();
        } catch (InterruptedException e) {
            // Exception handling
            e.printStackTrace();
        }
    }
}
