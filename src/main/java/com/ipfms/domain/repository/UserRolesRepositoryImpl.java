package com.ipfms.domain.repository;

import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Component;
import com.ipfms.domain.model.userRoles;
import io.katharsis.queryspec.QuerySpec;
import io.katharsis.repository.ResourceRepositoryBase;
import io.katharsis.resource.list.ResourceList;


@Component
public class UserRolesRepositoryImpl extends ResourceRepositoryBase<UserRoles, Long> {
    private Map<Long, userRoles> userroles = new HashMap<>();
    public UserRolesRepositoryImpl() {
        super(UserRoles.class);
    }
    @Override
    public synchronized ResourceList<userRoles> findAll(QuerySpec querySpec) {
        return querySpec.apply(userroles.values());
    }
}