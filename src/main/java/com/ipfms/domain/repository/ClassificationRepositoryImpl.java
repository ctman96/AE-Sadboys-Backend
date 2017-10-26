package com.ipfms.domain.repository;

import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Component;
import com.ipfms.domain.model.Classification;
import io.katharsis.queryspec.QuerySpec;
import io.katharsis.repository.ResourceRepositoryBase;
import io.katharsis.resource.list.ResourceList;
import java.util.Date;


@Component
public class ClassificationRepositoryImpl extends ResourceRepositoryBase<Classification, Long> {
    private Map<Long, Classification> classifications = new HashMap<>();
    public ClassificationRepositoryImpl() {
        super(Classification.class);
        classifications.put(1L, new Classification(1L, "test", "F", new Date())); //Test
    }

    @Override
    public ResourceList<Classification> findAll(QuerySpec querySpec) {
        return querySpec.apply(classifications.values());
    }
}
