package com.ipfms.domain.repository;

import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Component;
import com.ipfms.domain.model.userLocations;
import io.katharsis.queryspec.QuerySpec;
import io.katharsis.repository.ResourceRepositoryBase;
import io.katharsis.resource.list.ResourceList;


@Component
public class userLocationRepositoryImpl extends ResourceRepositoryBase<userLocations, Long> {
    private Map<Long, userLocations> userLoc = new HashMap<>();
    public UserLocationRepositoryImpl() {
        super(userLocations.class);
    }
    @Override
    public synchronized ResourceList<userLocations> findAll(QuerySpec querySpec) {
        return querySpec.apply(userLoc.values());
    }
}