package com.ipfms.domain.repository;

import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Component;
import com.ipfms.domain.model.Location;
import io.katharsis.queryspec.QuerySpec;
import io.katharsis.repository.ResourceRepositoryBase;
import io.katharsis.resource.list.ResourceList;

/**
 * Created by Cody on 2017-10-21.
 */

@Component
public class LocationRepositoryImpl extends ResourceRepositoryBase<Location, Integer> {
    private Map<Integer, Location> locations = new HashMap<>();
    public LocationRepositoryImpl() {
        super(Location.class);
    }
    @Override
    public synchronized ResourceList<Location> findAll(QuerySpec querySpec) {
        return querySpec.apply(locations.values());
    }
}
