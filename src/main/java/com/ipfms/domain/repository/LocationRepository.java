package com.ipfms.domain.repository;

import com.ipfms.domain.model.Location;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Component
@Repository
public interface LocationRepository extends PagingAndSortingRepository<Location, Integer> {
    Location findById(Integer id);
    Page<Location> findAll(Pageable pageable);
}
