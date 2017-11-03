package com.ipfms.domain.repository;

import com.ipfms.domain.model.Classification;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Component
@Repository
public interface ClassificationRepository extends CrudRepository<Classification, Integer>{

}
