package com.ipfms.domain.repository;

import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Component;
import com.ipfms.domain.model.CustomAttributeValue;
import io.katharsis.queryspec.QuerySpec;
import io.katharsis.repository.ResourceRepositoryBase;
import io.katharsis.resource.list.ResourceList;

@Component
public class CustomAttributeValueRepositoryImpl extends ResourceRepositoryBase<CustomAttributeValue, Integer> {
    private Map<Integer, CustomAttributeValue> customAttributeValues = new HashMap<>();
    public CustomAttributeValueRepositoryImpl() {
        super(CustomAttributeValue.class);
    }

    @Override
    public ResourceList<CustomAttributeValue> findAll(QuerySpec querySpec) {
        return querySpec.apply(customAttributeValues.values());
    }
}
