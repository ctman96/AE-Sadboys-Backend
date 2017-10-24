package com.ipfms.domain.repository;

import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Component;
import com.ipfms.domain.model.Roles;
import io.katharsis.queryspec.QuerySpec;
import io.katharsis.repository.ResourceRepositoryBase;
import io.katharsis.resource.list.ResourceList;


@Component
public class RoleRepositoryImpl extends ResourceRepositoryBase<Roles, Long> {
    private Map<Long, Roles> roles = new HashMap<>();
    public RoleRepositoryImpl() {
        super(Roles.class);
    }
    @Override
    public synchronized ResourceList<Roles> findAll(QuerySpec querySpec) {
        return querySpec.apply(roles.values());
    }
}