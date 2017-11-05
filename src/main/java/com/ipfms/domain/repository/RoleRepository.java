package com.ipfms.domain.repository;

import com.ipfms.domain.model.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Component
@Repository
public interface RoleRepository extends CrudRepository<Role, Integer>{
    Role findById(Integer id);
    Page<Role> findAll(Pageable pageable);
}