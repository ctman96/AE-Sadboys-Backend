package com.ipfms.domain.repository;

import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Component;
import com.ipfms.domain.model.RecordClassifications;
import io.katharsis.queryspec.QuerySpec;
import io.katharsis.repository.ResourceRepositoryBase;
import io.katharsis.resource.list.ResourceList;

@Component
public class RecordClassificationsRepositoryImpl extends ResourceRepositoryBase<RecordClassifications, Long> {
    private Map<Long, RecordClassifications> recordClassifications = new HashMap<>();
    public RecordClassificationsRepositoryImpl() {
        super(RecordClassifications.class);
    }

    @Override
    public ResourceList<RecordClassifications> findAll(QuerySpec querySpec) {
        return querySpec.apply(recordClassifications.values());
    }
}
