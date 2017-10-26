package com.ipfms.domain.repository;

import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Component;
import com.ipfms.domain.model.Classifications;
import io.katharsis.queryspec.QuerySpec;
import io.katharsis.repository.ResourceRepositoryBase;
import io.katharsis.resource.list.ResourceList;


@Component
public class ClassificationsRepositoryImpl extends ResourceRepositoryBase<Classifications, Long> {
    private Map<Long, Classifications> classifications = new HashMap<>();
    public ClassificationsRepositoryImpl() {
        super(Classifications.class);
    }

    @Override
    public ResourceList<Classifications> findAll(QuerySpec querySpec) {
        return querySpec.apply(classifications.values());
    }
}
