package com.ipfms.domain.repository;

import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Component;
import com.ipfms.domain.model.UserLocation;
import io.katharsis.queryspec.QuerySpec;
import io.katharsis.repository.ResourceRepositoryBase;
import io.katharsis.resource.list.ResourceList;


@Component
public class UserLocationRepositoryImpl extends ResourceRepositoryBase<UserLocation, Long> {
    private Map<Long, UserLocation> userLoc = new HashMap<>();
    public UserLocationRepositoryImpl() {
        super(UserLocation.class);
    }
    @Override
    public synchronized ResourceList<UserLocation> findAll(QuerySpec querySpec) {
        return querySpec.apply(userLoc.values());
    }
}