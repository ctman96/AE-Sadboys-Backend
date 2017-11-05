package com.ipfms.domain.repository;

import com.ipfms.domain.model.ClassHierarchy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Component
@Repository
public interface ClassHierarchyRepository extends CrudRepository<ClassHierarchy, Integer>{
    ClassHierarchy findById(Integer id);
    Page<ClassHierarchy> findAll(Pageable pageable);
}