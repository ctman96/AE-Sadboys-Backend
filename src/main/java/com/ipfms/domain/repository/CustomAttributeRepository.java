package com.ipfms.domain.repository;

import com.ipfms.domain.model.CustomAttribute;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

/**
 * Spring Data repository managing CustomAttribute CRUD
 */
@Component
@Repository
public interface CustomAttributeRepository extends CrudRepository<CustomAttribute, Integer>{
    CustomAttribute findById(Integer id);
    Page<CustomAttribute> findAll(Pageable pageable);
}

