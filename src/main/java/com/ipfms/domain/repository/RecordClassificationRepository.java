package com.ipfms.domain.repository;

import com.ipfms.domain.model.RecordClassification;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Component
@Repository
public interface RecordClassificationRepository extends CrudRepository<RecordClassification, Integer>{
    //RecordClassification findById(Integer recordId);
    RecordClassification findAllBy();
}

