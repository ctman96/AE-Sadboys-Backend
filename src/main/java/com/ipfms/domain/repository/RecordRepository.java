package com.ipfms.domain.repository;

import com.ipfms.domain.model.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * Spring Data repository managing Record CRUD
 */
@Component
@Repository
public interface RecordRepository extends CrudRepository<Record, Integer>{
    Record findById(Integer id);
    List<Record> findByNumberOrTitleOrConsignmentCode(String number, String title, String consignmentCode);
    Page<Record> findAll(Pageable pageable);

    @Query("SELECT r FROM Record r " +
            "WHERE (:classification IS NULL OR :classification MEMBER r.classifications) " +
            "AND (:location IS NULL OR :location = r.location) " +
            "AND (:schedule IS NULL OR :schedule = r.schedule) " +
            "AND (:state IS NULL OR :state = r.state) " +
            "AND (:type IS NULL OR :type = r.type)")
    List<Record> filteredFind(
            @Param("classification")Classification classification,
            @Param("location")Location location,
            @Param("schedule")RetentionSchedule schedule,
            @Param("state")RecordState state,
            @Param("type")RecordType type
    );

}

