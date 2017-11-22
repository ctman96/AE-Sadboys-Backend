package com.ipfms.domain.repository;

import com.ipfms.domain.model.RecordType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

/**
 * Spring Data repository managing RecordType CRUD
 */
@Component
@Repository
public interface RecordTypeRepository extends CrudRepository<RecordType, Integer>{
    RecordType findById(Integer id);
    RecordType findAllBy();
}

