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
            @RequestParam(value = "page", required = false) Integer page){
        System.out.println("In 'search'");
        if(size == null){
            size = 10;
        }
        if(page == null){
            page = 0;
        }
        EntityManager em = entityManagerFactory.createEntityManager();
        FullTextEntityManager fullTextEntityManager =
                org.hibernate.search.jpa.Search.getFullTextEntityManager(em);
        em.getTransaction().begin();

        QueryBuilder qbr = fullTextEntityManager.getSearchFactory()
                .buildQueryBuilder().forEntity(Record.class).get();
        QueryBuilder qbc = fullTextEntityManager.getSearchFactory()
                .buildQueryBuilder().forEntity(Container.class).get();

        org.apache.lucene.search.Query luceneQueryR = qbr
                .keyword()
                .onFields("number", "title", "consignmentCode")
                .matching(query)
                .createQuery();
        org.apache.lucene.search.Query luceneQueryC = qbc
                .keyword()
                .onFields("number", "title", "consignmentCode")
                .matching(query)
                .createQuery();
        javax.persistence.Query jpaQueryR =
                fullTextEntityManager.createFullTextQuery(luceneQueryR, Record.class);
        javax.persistence.Query jpaQueryC =
                fullTextEntityManager.createFullTextQuery(luceneQueryC, Container.class);

        List<Record> resultR = jpaQueryR.getResultList();
        List<Container> resultC = jpaQueryC.getResultList();
        List<SearchResult> results = new ArrayList<>();

        for(Container c : resultC){
            results.add(new SearchResult(c));
        }
        for(Record r : resultR){
            results.add(new SearchResult(r));
        }
        if (results.size() == 0) {
            throw new EntityNotFoundException("No Results found");
        }
        //TODO Add filter requestparams, filter results before paging

        Pageable pageable = new PageRequest(page, size);

        int start = pageable.getOffset();
        int end = (start + pageable.getPageSize()) > results.size() ? results.size() : (start + pageable.getPageSize());
        Page<SearchResult> pageResult = new PageImpl<SearchResult>(results.subList(start, end), pageable, results.size());

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
