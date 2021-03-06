package com.ipfms.domain.repository;

import com.ipfms.domain.model.CustomAttributeLookup;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

/**
 * JSpring Data repository managing CustomAttributeLookup CRUD
 */
@Component
@Repository
public interface CustomAttributeLookupRepository extends CrudRepository<CustomAttributeLookup, Integer>{
    CustomAttributeLookup findById(Integer id);
}
