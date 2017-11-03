package com.ipfms.domain.repository;

import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Component;
import com.ipfms.domain.model.UserRole;
import io.katharsis.queryspec.QuerySpec;
import io.katharsis.repository.ResourceRepositoryBase;
import io.katharsis.resource.list.ResourceList;


@Component
public class UserRoleRepositoryImpl extends ResourceRepositoryBase<UserRole, Integer> {
    private Map<Integer, UserRole> userRoles = new HashMap<>();
    public UserRoleRepositoryImpl() {
        super(UserRole.class);
    }
    @Override
    public synchronized ResourceList<UserRole> findAll(QuerySpec querySpec) {
        return querySpec.apply(userRoles.values());
    }
}