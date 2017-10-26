package com.ipfms.domain.repository;

import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Component;
import com.ipfms.domain.model.CustomAttributes;
import io.katharsis.queryspec.QuerySpec;
import io.katharsis.repository.ResourceRepositoryBase;
import io.katharsis.resource.list.ResourceList;

public class CustomAttributesRepositoryImpl extends ResourceRepositoryBase<CustomAttributes, Long>{
    private Map<Long, CustomAttributes> customAttributes = new HashMap<>();
    public CustomAttributesRepositoryImpl() {
        super(CustomAttributes.class);
    }

    @Override
    public ResourceList<CustomAttributes> findAll(QuerySpec querySpec) {
        return querySpec.apply(customAttributes.values());
    }
}
