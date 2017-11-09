package com.ipfms.domain.repository;

import com.ipfms.domain.model.LabelColour;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Component
@Repository
public interface LabelColourRepository extends PagingAndSortingRepository<LabelColour, Integer> {
    LabelColour findByKey(String key);
    Page<LabelColour> findAll(Pageable pageable);
}

