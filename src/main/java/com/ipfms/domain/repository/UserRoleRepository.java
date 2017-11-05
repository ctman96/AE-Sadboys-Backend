package com.ipfms.domain.repository;

import com.ipfms.domain.model.UserRole;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Component
@Repository
public interface UserRoleRepository extends CrudRepository<UserRole, Integer>{
    //UserRole findById(Integer userId);
    UserRole findAllBy();
}