package com.ipfms.domain.repository;

import com.ipfms.domain.model.Record;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Component
@Repository
public interface RecordRepository extends CrudRepository<Record, Integer>{
    Record findById(Integer id);
    List<Record> findByNumberOrTitleOrConsignmentCode(String number, String title, String consignmentCode);
    Page<Record> findAll(Pageable pageable);
}

