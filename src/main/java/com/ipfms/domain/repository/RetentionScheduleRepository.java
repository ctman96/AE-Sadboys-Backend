package com.ipfms.domain.repository;

import com.ipfms.domain.model.RetentionSchedule;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

/**
 * Spring Data repository managing RetentionSchedule CRUD
 */
@Component
@Repository
public interface RetentionScheduleRepository extends CrudRepository<RetentionSchedule, Integer> {
    RetentionSchedule findById(Integer id);
}
