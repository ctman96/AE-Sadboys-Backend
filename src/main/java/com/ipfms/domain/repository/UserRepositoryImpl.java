package com.ipfms.domain.repository;

import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Component;
import com.ipfms.domain.model.User;
import io.katharsis.queryspec.QuerySpec;
import io.katharsis.repository.ResourceRepositoryBase;
import io.katharsis.resource.list.ResourceList;


@Component
public class UserRepositoryImpl extends ResourceRepositoryBase<User, Long> {
    private Map<Long, User> users = new HashMap<>();
    public UserRepositoryImpl() {
        super(User.class);
    }
    @Override
    public synchronized ResourceList<User> findAll(QuerySpec querySpec) {
        return querySpec.apply(users.values());
    }
}