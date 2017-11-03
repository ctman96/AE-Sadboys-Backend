package com.ipfms.domain.repository;

import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Component;
import com.ipfms.domain.model.LabelColor;
import io.katharsis.queryspec.QuerySpec;
import io.katharsis.repository.ResourceRepositoryBase;
import io.katharsis.resource.list.ResourceList;

/**
 * Created by Cody on 2017-10-21.
 */

@Component
public class LabelColorRepositoryImpl extends ResourceRepositoryBase<LabelColor, String> {
    private Map<String, LabelColor> labelColors = new HashMap<>();
    public LabelColorRepositoryImpl() {
        super(LabelColor.class);
    }
    @Override
    public synchronized ResourceList<LabelColor> findAll(QuerySpec querySpec) {
        return querySpec.apply(labelColors.values());
    }
}
