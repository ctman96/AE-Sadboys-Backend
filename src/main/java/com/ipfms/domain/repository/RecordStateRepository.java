package com.ipfms.domain.repository;

import com.ipfms.domain.model.RecordState;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

/**
 * Spring Data repository managing RecordState CRUD
 */
@Component
@Repository
public interface RecordStateRepository extends CrudRepository<RecordState, Integer>{
    RecordState findById(Integer id);
}
