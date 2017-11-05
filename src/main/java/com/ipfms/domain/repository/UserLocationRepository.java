package com.ipfms.domain.repository;

import com.ipfms.domain.model.UserLocation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Component
@Repository
public interface UserLocationRepository extends CrudRepository<UserLocation, Integer>{
    //UserLocation findById(Integer userId);
    UserLocation findAllBy();
}
