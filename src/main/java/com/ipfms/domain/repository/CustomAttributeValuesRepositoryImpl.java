package com.ipfms.domain.repository;

import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Component;
import com.ipfms.domain.model.CustomAttributeValues;
import io.katharsis.queryspec.QuerySpec;
import io.katharsis.repository.ResourceRepositoryBase;
import io.katharsis.resource.list.ResourceList;

@Component
public class CustomAttributeValuesRepositoryImpl extends ResourceRepositoryBase<CustomAttributeValues, Long> {
    private Map<Long, CustomAttributeValues> customAttributeValues = new HashMap<>();
    public CustomAttributeValuesRepositoryImpl() {
        super(CustomAttributeValues.class);
    }

    @Override
    public ResourceList<CustomAttributeValues> findAll(QuerySpec querySpec) {
        return querySpec.apply(customAttributeValues.values());
    }
}
