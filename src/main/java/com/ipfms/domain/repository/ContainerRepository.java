package com.ipfms.domain.repository;

import com.ipfms.domain.model.Container;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Component
@Repository
public interface ContainerRepository extends CrudRepository<Container, Integer>{
    Container findById(Integer id);
    Page<Container> findAll(Pageable pageable);
}