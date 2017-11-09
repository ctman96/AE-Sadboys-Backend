package com.ipfms.domain.repository;

import com.ipfms.domain.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Component
@Repository
public interface UserRepository extends CrudRepository<User, Integer>{
    User findById(Integer id);
    User findByUserId(String userId);
    Page<User> findAll(Pageable pageable);
}