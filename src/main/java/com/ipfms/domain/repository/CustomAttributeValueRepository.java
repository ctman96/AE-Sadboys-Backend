package com.ipfms.domain.repository;

import com.ipfms.domain.model.CustomAttributeValue;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

/**
 * Spring Data repository managing CustomAttributeValue CRUD
 */
@Component
@Repository
public interface CustomAttributeValueRepository extends CrudRepository<CustomAttributeValue, Integer>{
    CustomAttributeValue findById(Integer id);
    Page<CustomAttributeValue> findAll(Pageable pageable);
}

