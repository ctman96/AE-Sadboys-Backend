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
public class ClassificationRepositoryImpl extends ResourceRepositoryBase<Classification, Integer> {
    private Map<Integer, Classification> classifications = new HashMap<>();
    public ClassificationRepositoryImpl() {
        super(Classification.class);
        classifications.put(1, new Classification(1, "test", "F", new Date())); //Test
    }

    @Override
    public ResourceList<Classification> findAll(QuerySpec querySpec) {
        return querySpec.apply(classifications.values());
    }
}
