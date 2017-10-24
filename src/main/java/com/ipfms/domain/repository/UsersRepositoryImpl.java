package com.ipfms.domain.repository;

import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Component;
import com.ipfms.domain.model.Users;
import io.katharsis.queryspec.QuerySpec;
import io.katharsis.repository.ResourceRepositoryBase;
import io.katharsis.resource.list.ResourceList;


@Component
public class UserRepositoryImpl extends ResourceRepositoryBase<Users, Long> {
    private Map<Long, Users> users = new HashMap<>();
    public UsersRepositoryImpl() {
        super(Users.class);
    }
    @Override
    public synchronized ResourceList<Users> findAll(QuerySpec querySpec) {
        return querySpec.apply(users.values());
    }
}