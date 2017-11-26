package com.ipfms.controllers;

import com.ipfms.domain.model.*;
import com.ipfms.domain.repository.*;
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
import org.springframework.web.util.UriUtils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Rest Controller -
 * Handles RequestMapping for the '/search' namespace
 */
@RestController
@RequestMapping("/search")
public class SearchController {
    private final EntityManagerFactory entityManagerFactory;
    private final ContainerRepository containerRepository;
    private final RecordRepository recordRepository;
    private final ClassificationRepository classificationRepository;
    private final LocationRepository locationRepository;
    private final RetentionScheduleRepository retentionScheduleRepository;
    private final RecordStateRepository recordStateRepository;
    private final RecordTypeRepository recordTypeRepository;

    @Autowired
    public SearchController(EntityManagerFactory entityManagerFactory,
                            ContainerRepository containerRepository,
                            RecordRepository recordRepository,
                            ClassificationRepository classificationRepository,
                            LocationRepository locationRepository,
                            RetentionScheduleRepository retentionScheduleRepository,
                            RecordStateRepository recordStateRepository,
                            RecordTypeRepository recordTypeRepository) {
        this.entityManagerFactory = entityManagerFactory;
        this.containerRepository = containerRepository;
        this.recordRepository = recordRepository;
        this.classificationRepository = classificationRepository;
        this.locationRepository = locationRepository;
        this.retentionScheduleRepository = retentionScheduleRepository;
        this.recordStateRepository = recordStateRepository;
        this.recordTypeRepository = recordTypeRepository;
    }

    /**
     * Returns a Response Entity containing a page of HATEOAS PagedResources of SearchResult objects, after
     * performing a quick search or a filtered, full text search with the given parameters.
     * Return page's size and page number are determined by the size and page parameters
     * <p>
     * Mapped to '/search', with all parameters as RequestParameters
     *
     * @param query             the string used as a query for the fulltext search
     * @param quickSearch       Whether or not you want full(fulltext, filtered) or quick(exact) searching
     * @param size              the size of pages you want returned (optional, default 10)
     * @param page              the page number, for the given size (optional, default 0)
     * @param includeRecords    whether or not to include Records in the SearchResults (optional, default true)
     * @param includeContainers whether or not to include Containers in the Search Results (optional, default true)
     * @param classification    the classification Id to use to filter Records (optional, default null)
     * @param createdAtUnix     the createdAt date value used to filter Records and Containers, unix stamp (optional, default null)
     * @param updatedAtUnix     the updatedAt date value used to filter Records and Containers, unix stamp (optional, default null)
     * @param closedAtUnix      the closedAt date value used to filter Records (optional, default null)
     * @param location          the location Id used to filter Records (optional, default null)
     * @param schedule          the schedule Id used to filter Records (optional, default null)
     * @param state             the state Id used to filter Records (optional, default null)
     * @param type              the type Id used to filter Records (optional, default null)
     * @return                  Response Entity containing a page of HATEOAS PagedResources of SearchResult objects
     */
    @RequestMapping()
    public ResponseEntity<PagedResources<SearchResult>> search(
            //Search Query
            @RequestParam(value="query", required=false) String query,

            @RequestParam(value="qs", required=false) Boolean quickSearch,

            //Pagination settings
            @RequestParam(value = "pageSize", required = false) Integer size,
            @RequestParam(value = "page", required = false) Integer page,

            //Whether to include records/containers
            @RequestParam(value="records", required = false) Boolean includeRecords,
            @RequestParam(value="containers", required= false) Boolean includeContainers,

            //Filters
            @RequestParam(value="classification", required=false) Integer classification,
            @RequestParam(value="created", required=false) Long createdAtUnix,
            @RequestParam(value="updated", required=false) Long updatedAtUnix,
            @RequestParam(value="closed", required=false) Long closedAtUnix,
            @RequestParam(value="location", required=false) Integer location,
            @RequestParam(value="schedule", required=false) Integer schedule,
            @RequestParam(value="state", required=false) Integer state,
            @RequestParam(value="type", required=false) Integer type){

        System.out.println("In 'search'");

        //Check for null parameters, set to default values
        if(query == null || query.equals("null")){
            query=null;
        }else{
            try {
                UriUtils.decode(query, "UTF-8");
            }
            catch(UnsupportedEncodingException err){
                System.out.println(err);
            }
        }
        System.out.println("With Query: "+query);
        if(quickSearch == null){
            quickSearch = false;
        }
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
        Date createdAt = null;
        Date updatedAt = null;
        Date closedAt = null;
        if (createdAtUnix != null){
            createdAt = new Date(createdAtUnix*1000L);
        }
        if (updatedAtUnix != null){
            updatedAt = new Date(updatedAtUnix*1000L);
        }
        if (closedAtUnix != null){
            closedAt = new Date(closedAtUnix*1000L);
        }

        List<SearchResult> results = new ArrayList<>();

        if(quickSearch){
            if (query != null) {
                results.addAll(quickSearch(query));
            }
        }
        else if (query != null) {
            results.addAll(
                    fullSearch(query, includeRecords, includeContainers, classification,
                            createdAt, updatedAt, closedAt, location, schedule, state, type)
            );
        }
        else {
            results.addAll(getAllResults(includeRecords, includeContainers, classification,
                    createdAt, updatedAt, closedAt, location, schedule, state, type));
        }

        Pageable pageable = new PageRequest(page, size);

        int start = pageable.getOffset();
        int end = (start + pageable.getPageSize()) > results.size() ? results.size() : (start + pageable.getPageSize());
        Page<SearchResult> pageResult = new PageImpl<>(results.subList(start, end), pageable, results.size());

        PagedResources.PageMetadata metadata = new PagedResources.PageMetadata(
                pageResult.getSize(), pageResult.getNumber(),
                pageResult.getTotalElements(), pageResult.getTotalPages());

        PagedResources<SearchResult> resources = new PagedResources<>(pageResult.getContent(), metadata);
        System.out.println("Exiting 'search'");
        return ResponseEntity.ok(resources);
    }

    private List<SearchResult> quickSearch(String query){
        System.out.println("In 'quickSearch'");

        List<SearchResult> results = new ArrayList<>();

        List<Record> resultR = recordRepository.findByNumberOrTitleOrConsignmentCode(query, query, query);
        //Records

        for(Record r : resultR){
            results.add(new SearchResult(r));
        }

        //Containers
        List<Container> resultC = containerRepository.findByNumberOrTitleOrConsignmentCode(query, query, query);

        for(Container c : resultC){
            results.add(new SearchResult(c));
        }

        System.out.println("Exiting 'quickSearch'");
        return results;
    }

    private List<SearchResult> getAllResults(Boolean includeRecords, Boolean includeContainers, Integer  classification,
                                             Date createdAt, Date updatedAt, Date closedAt,
                                             Integer location, Integer schedule, Integer state, Integer type){
        System.out.println("In 'getAllResults'");

        List<SearchResult> results = new ArrayList<>();

        Classification c = classificationRepository.findById(classification);
        Location l = locationRepository.findById(location);
        RetentionSchedule sch = retentionScheduleRepository.findById(schedule);
        RecordState st = recordStateRepository.findById(state);
        RecordType t = recordTypeRepository.findById(type);



        if(includeRecords) {
            List<Record> resultR = recordRepository
                    .filteredFind(
                            c, l, sch, st, t
                    );

            for (Record r : resultR){
                Boolean createfilter = ((createdAt == null) || compareDates(createdAt, r.getCreatedAt()));
                Boolean updatefilter = ((updatedAt == null) || compareDates(updatedAt, r.getUpdatedAt()));
                Boolean closedfilter = ((closedAt == null) || compareDates(closedAt, r.getClosedAt()));
                if (createfilter && updatefilter && closedfilter) {
                    results.add(new SearchResult(r));
                }
            }
        }

        //Build and execute Container Query
        if(includeContainers && ((classification == null) && (location == null) && (schedule == null) && (state == null) && (type == null))) {

            List<Container> resultC = containerRepository.findAll();

            for (Container con : resultC){
                Boolean createfilter = ((createdAt == null) || compareDates(createdAt, con.getCreatedAt()));
                Boolean updatefilter = ((updatedAt == null) || compareDates(updatedAt, con.getUpdatedAt()));
                if(createfilter && updatefilter) {
                    results.add(new SearchResult(con));
                }
            }
        }

        System.out.println("Exiting 'getAllResults'");
        return results;
    }

    private List<SearchResult> fullSearch(String query, Boolean includeRecords, Boolean includeContainers,
                                                      Integer classification, Date createdAt, Date updatedAt, Date closedAt,
                                                      Integer location, Integer schedule, Integer state, Integer type){
        System.out.println("In 'fullSearch'");
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
                    .onFields("number", "title", "consignmentCode", "notes.text")
                    .matching(query)
                    .createQuery();

            javax.persistence.Query jpaQueryR =
                    fullTextEntityManager.createFullTextQuery(luceneQueryR, Record.class);

            List<Record> resultR = jpaQueryR.getResultList();

            //Filter and add to full results
            results.addAll(
                    filterRecords(resultR, classification,
                            createdAt, updatedAt, closedAt,
                            location, schedule, state, type
                    )
            );
        }

        //Build and execute Container Query
        if(includeContainers && ((classification == null) && (location == null) && (schedule == null) && (state == null) && (type == null))) {
            QueryBuilder qbc = fullTextEntityManager.getSearchFactory()
                    .buildQueryBuilder().forEntity(Container.class).get();


            org.apache.lucene.search.Query luceneQueryC = qbc
                    .keyword()
                    .onFields("number", "title", "consignmentCode", "notes.text")
                    .matching(query)
                    .createQuery();

            javax.persistence.Query jpaQueryC =
                    fullTextEntityManager.createFullTextQuery(luceneQueryC, Container.class);
            List<Container> resultC = jpaQueryC.getResultList();

            //Filter and add to full results
            results.addAll(
                    filterContainers( resultC, createdAt, updatedAt)
            );
        }

        System.out.println("Exiting 'fullSearch'");
        return results;
    }

    private List<SearchResult> filterRecords(List<Record> records,
                                             Integer classification,
                                             Date createdAt,
                                             Date updatedAt,
                                             Date closedAt,
                                             Integer location,
                                             Integer schedule,
                                             Integer state,
                                             Integer type){
        System.out.println("In 'filterRecords'");
        List<SearchResult> results = new ArrayList<>();
        for (Record r : records) {
            Boolean doAdd = true;
            doAdd = doAdd && ((classification == null) ? true : checkClassifications(r, classification));
            doAdd = doAdd && ((createdAt == null) || compareDates(r.getCreatedAt(), createdAt));
            doAdd = doAdd && ((updatedAt == null) || compareDates(r.getUpdatedAt(), updatedAt));
            doAdd = doAdd && ((closedAt == null) || compareDates(r.getClosedAt(), closedAt));
            doAdd = doAdd && ((location == null) || r.getLocation().getId().equals(location));
            doAdd = doAdd && ((schedule == null) || r.getSchedule().getId().equals(schedule));
            doAdd = doAdd && ((state == null) || r.getState().getId().equals(state));
            doAdd = doAdd && ((type == null) || r.getType().getId().equals(type));
            if (doAdd) {
                results.add(new SearchResult(r));
            }
        }
        System.out.println("Exiting 'filterRecords'");
        return results;
    }

    private List<SearchResult> filterContainers(List<Container> containers, Date createdAt, Date updatedAt){
        System.out.println("In 'filterContainers'");
        List<SearchResult> results = new ArrayList<>();
        for(Container c : containers){
            Boolean doAdd = (
                    ((createdAt == null) || compareDates(c.getCreatedAt(), createdAt))
                    && ((updatedAt == null) ||compareDates(c.getUpdatedAt(), updatedAt)));
            //TODO: Do containers get filtered by anything else, depending on subrecords?
            if(doAdd) {
                results.add(new SearchResult(c));
            }
        }
        System.out.println("Exiting 'filterContainers'");
        return results;
    }

    //Classification Filter Helper
    private Boolean checkClassifications(Record record, Integer classificationVal){
        System.out.println("In 'checkClassifications'");
        if (record.getClassifications().isEmpty()){
            return false;
        }

        Boolean check = true;
        for (Classification c : record.getClassifications()){
            check = check && c.getId().equals(classificationVal);
        }
        System.out.println("Exiting 'checkClassifications'");
        return check;
    }

    //Date Comparison Helper
    private Boolean compareDates(Date date1, Date date2){
        //System.out.println("In 'compareDates'");
        SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMdd");
        //System.out.println("Date 1: " + date1 + "Date 2: " + date2);
        //System.out.println("Date 1: " + fmt.format(date1) + "Date 2: " + fmt.format(date2));
        //System.out.println("Exiting 'compareDates'");
        return fmt.format(date1).equals(fmt.format(date2));
    }

    /**
     * Reloads the search index.
     * Warning: The search index will be unavailable for the duration of the process
     * Should take approximately 1.5 hours with the given Data (RecordR-Data.sql)
     *
     * Note: Before adding Notes to the index, was <5 minutes. Needing to join tables slows
     * it down significantly
     */
    @RequestMapping("/reload-index")
    public void reload(){
        System.out.println("In 'reload'");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        FullTextEntityManager fullTextEntityManager =
                Search.getFullTextEntityManager(entityManager);
        try {
            fullTextEntityManager
                    .createIndexer(Record.class, Container.class)
                    .typesToIndexInParallel( 2 )
                    .batchSizeToLoadObjects( 50 )
                    .threadsToLoadObjects( 12 )
                    .idFetchSize( 150 )
                    .cacheMode(CacheMode.NORMAL)
                    .startAndWait();
        } catch (InterruptedException e) {
            System.out.println("Exiting 'reload'");
            // Exception handling
            e.printStackTrace();
        }
        System.out.println("Exiting 'reload'");
    }
}
