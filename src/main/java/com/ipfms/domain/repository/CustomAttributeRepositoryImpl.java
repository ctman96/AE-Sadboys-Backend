package com.ipfms.domain.repository;

import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Component;
import com.ipfms.domain.model.CustomAttribute;
import io.katharsis.queryspec.QuerySpec;
import io.katharsis.repository.ResourceRepositoryBase;
import io.katharsis.resource.list.ResourceList;

@Component
public class CustomAttributeRepositoryImpl extends ResourceRepositoryBase<CustomAttribute, Integer>{
    private Map<Integer, CustomAttribute> customAttributes = new HashMap<>();
    public CustomAttributeRepositoryImpl() {
        super(CustomAttribute.class);
    }

    @Override
    public ResourceList<CustomAttribute> findAll(QuerySpec querySpec) {
        return querySpec.apply(customAttributes.values());
    }
}
