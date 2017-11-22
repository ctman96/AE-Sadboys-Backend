package com.ipfms.domain.repository;

import com.ipfms.domain.model.Container;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Component
@Repository
public interface ContainerRepository extends CrudRepository<Container, Integer>{
    Container findById(Integer id);
    List<Container> findByNumberOrTitleOrConsignmentCode(String number, String title, String consignmentCode);
    Page<Container> findAll(Pageable pageable);
}