package com.ipfms.domain.repository;

import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Component;
import com.ipfms.domain.model.Container;
import io.katharsis.queryspec.QuerySpec;
import io.katharsis.repository.ResourceRepositoryBase;
import io.katharsis.resource.list.ResourceList;

/**
 * Created by Cody on 2017-10-21.
 */

@Component
public class ContainerRepositoryImpl extends ResourceRepositoryBase<Container, Long> {
    private Map<Long, Container> containers = new HashMap<>();
    public ContainerRepositoryImpl() {
        super(Container.class);
    }
    @Override
    public synchronized ResourceList<Container> findAll(QuerySpec querySpec) {
        return querySpec.apply(containers.values());
    }
}
