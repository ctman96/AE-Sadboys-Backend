package com.ipfms.controllers;

import com.ipfms.domain.model.Container;
import com.ipfms.domain.model.Record;
import com.ipfms.domain.model.SearchResult;
import com.ipfms.domain.repository.ContainerRepository;
import com.ipfms.domain.repository.RecordRepository;
import com.ipfms.exception.EntityNotFoundException;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.PagedResources;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/search")
public class SearchController {
    private final RecordRepository recordRepository;
    private final ContainerRepository containerRepository;
    private final EntityManagerFactory entityManagerFactory;

    @Autowired
    public SearchController(EntityManagerFactory entityManagerFactory, RecordRepository recordRepository, ContainerRepository containerRepository) {
        this.entityManagerFactory = entityManagerFactory;
        this.recordRepository = recordRepository;
        this.containerRepository = containerRepository;
    }

    @RequestMapping()
    public ResponseEntity<PagedResources<SearchResult>> search(
            @RequestParam(value="query", required=true) String query,
            @RequestParam(value = "pageSize", required = false) Integer size,
            @RequestParam(value = "page", required = false) Integer page,
            @RequestParam(value="records", required = false) Boolean includeRecords,
            @RequestParam(value="containers", required= false) Boolean includeContainers){
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

            for (Record r : resultR) {
                results.add(new SearchResult(r));
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
                results.add(new SearchResult(c));
            }
        }


        //TODO Add filter requestparams, filter results before paging

        Pageable pageable = new PageRequest(page, size);

        int start = pageable.getOffset();
        int end = (start + pageable.getPageSize()) > results.size() ? results.size() : (start + pageable.getPageSize());
        Page<SearchResult> pageResult = new PageImpl<>(results.subList(start, end), pageable, results.size());

        PagedResources.PageMetadata metadata = new PagedResources.PageMetadata(
                pageResult.getSize(), pageResult.getNumber(),
                pageResult.getTotalElements(), pageResult.getTotalPages());

        PagedResources<SearchResult> resources = new PagedResources<SearchResult>(pageResult.getContent(), metadata);
        System.out.println("Exiting 'showRecords'");
        return ResponseEntity.ok(resources);
    }

    @RequestMapping("/reload-index")
    public void reload(){
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        FullTextEntityManager fullTextEntityManager =
                Search.getFullTextEntityManager(entityManager);
        try {
            fullTextEntityManager.createIndexer().startAndWait();
        } catch (InterruptedException e) {
            // Exception handling
            e.printStackTrace();
        }
    }
}
