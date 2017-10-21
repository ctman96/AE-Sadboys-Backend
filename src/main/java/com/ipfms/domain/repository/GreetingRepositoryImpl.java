package com.ipfms.domain.repository;

import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Component;
import com.ipfms.domain.model.Greeting;
import io.katharsis.queryspec.QuerySpec;
import io.katharsis.repository.ResourceRepositoryBase;
import io.katharsis.resource.list.ResourceList;

/**
 * Created by Cody on 2017-10-21.
 * THIS IS AN EXAMPLE CLASS
 * See: https://dzone.com/articles/json-api-using-katharsis-amp-spring-boot
 * TODO: DELETE
 */

@Component
public class GreetingRepositoryImpl extends ResourceRepositoryBase<Greeting, Long> {
    private Map<Long, Greeting> greetings = new HashMap<>();
    public GreetingRepositoryImpl() {
        super(Greeting.class);
        greetings.put(1L, new Greeting(1L, "Hello World!"));
    }
    @Override
    public synchronized ResourceList<Greeting> findAll(QuerySpec querySpec) {
        return querySpec.apply(greetings.values());
    }
}
