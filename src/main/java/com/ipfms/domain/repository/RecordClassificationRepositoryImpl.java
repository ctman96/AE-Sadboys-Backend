package com.ipfms.domain.repository;

import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Component;
import com.ipfms.domain.model.RecordClassification;
import io.katharsis.queryspec.QuerySpec;
import io.katharsis.repository.ResourceRepositoryBase;
import io.katharsis.resource.list.ResourceList;

@Component
public class RecordClassificationRepositoryImpl extends ResourceRepositoryBase<RecordClassification, Long> {
    private Map<Long, RecordClassification> recordClassifications = new HashMap<>();
    public RecordClassificationRepositoryImpl() {
        super(RecordClassification.class);
    }

    @Override
    public ResourceList<RecordClassification> findAll(QuerySpec querySpec) {
        return querySpec.apply(recordClassifications.values());
    }
}
