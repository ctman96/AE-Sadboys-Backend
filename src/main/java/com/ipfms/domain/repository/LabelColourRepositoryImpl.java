package com.ipfms.domain.repository;

import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Component;
import com.ipfms.domain.model.LabelColour;
import io.katharsis.queryspec.QuerySpec;
import io.katharsis.repository.ResourceRepositoryBase;
import io.katharsis.resource.list.ResourceList;

/**
 * Created by Cody on 2017-10-21.
 */

@Component
public class LabelColourRepositoryImpl extends ResourceRepositoryBase<LabelColour, String> {
    private Map<String, LabelColour> labelColours = new HashMap<>();
    public LabelColourRepositoryImpl() {
        super(LabelColour.class);
    }
    @Override
    public synchronized ResourceList<LabelColour> findAll(QuerySpec querySpec) {
        return querySpec.apply(labelColours.values());
    }
}
