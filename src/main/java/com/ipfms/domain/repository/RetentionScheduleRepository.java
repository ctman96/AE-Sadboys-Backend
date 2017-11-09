package com.ipfms.domain.repository;

import com.ipfms.domain.model.RetentionSchedule;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Component
@Repository
public interface RetentionScheduleRepository extends CrudRepository<RetentionSchedule, Integer> {
    RetentionSchedule findById(Integer id);
}
