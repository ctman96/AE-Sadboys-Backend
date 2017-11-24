package com.ipfms.domain.repository;

import com.ipfms.domain.model.Classification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

/**
 * Spring Data repository managing Classification CRUD
 */
@Component
@Repository
public interface ClassificationRepository extends PagingAndSortingRepository<Classification, Integer> {
    Classification findById(Integer id);
    Classification findByName(String name);
    Page<Classification> findAll(Pageable pageable);
    Page<Classification> findByOrderByNameAsc(Pageable pageable);
}
