package com.ipfms.domain.repository;

import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Component;
import com.ipfms.domain.model.UserLocations;
import io.katharsis.queryspec.QuerySpec;
import io.katharsis.repository.ResourceRepositoryBase;
import io.katharsis.resource.list.ResourceList;


@Component
public class UserLocationRepositoryImpl extends ResourceRepositoryBase<UserLocations, Long> {
    private Map<Long, UserLocations> userLoc = new HashMap<>();
    public UserLocationRepositoryImpl() {
        super(UserLocations.class);
    }
    @Override
    public synchronized ResourceList<UserLocations> findAll(QuerySpec querySpec) {
        return querySpec.apply(userLoc.values());
    }
}