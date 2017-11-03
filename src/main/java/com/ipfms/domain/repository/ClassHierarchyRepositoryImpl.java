package com.ipfms.domain.repository;

import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Component;
import com.ipfms.domain.model.ClassHierarchy;
import io.katharsis.queryspec.QuerySpec;
import io.katharsis.repository.ResourceRepositoryBase;
import io.katharsis.resource.list.ResourceList;
import org.springframework.stereotype.Repository;

@Component
@Repository
public class ClassHierarchyRepositoryImpl extends ResourceRepositoryBase<ClassHierarchy, Integer> {
    private Map<Integer, ClassHierarchy> classHierarchy = new HashMap<>();
    public ClassHierarchyRepositoryImpl() {
        super(ClassHierarchy.class);
    }

    @Override
    public ResourceList<ClassHierarchy> findAll(QuerySpec querySpec) {
        return querySpec.apply(classHierarchy.values());
    }
}
