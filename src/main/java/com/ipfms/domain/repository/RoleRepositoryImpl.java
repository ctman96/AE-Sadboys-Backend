package com.ipfms.domain.repository;

import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Component;
import com.ipfms.domain.model.Role;
import io.katharsis.queryspec.QuerySpec;
import io.katharsis.repository.ResourceRepositoryBase;
import io.katharsis.resource.list.ResourceList;


@Component
public class RoleRepositoryImpl extends ResourceRepositoryBase<Role, Long> {
    private Map<Long, Role> roles = new HashMap<>();
    public RoleRepositoryImpl() {
        super(Role.class);
    }
    @Override
    public synchronized ResourceList<Role> findAll(QuerySpec querySpec) {
        return querySpec.apply(roles.values());
    }
}