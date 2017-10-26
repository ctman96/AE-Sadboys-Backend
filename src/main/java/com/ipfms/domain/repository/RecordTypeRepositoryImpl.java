package com.ipfms.domain.repository;

import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Component;
import com.ipfms.domain.model.RecordType;
import io.katharsis.queryspec.QuerySpec;
import io.katharsis.repository.ResourceRepositoryBase;
import io.katharsis.resource.list.ResourceList;

/**
 * Created by Cody on 2017-10-21.
 */

@Component
public class RecordTypeRepositoryImpl extends ResourceRepositoryBase<RecordType, Long> {
    private Map<Long, RecordType> recordTypes = new HashMap<>();
    public RecordTypeRepositoryImpl() {
        super(RecordType.class);
    }
    @Override
    public synchronized ResourceList<RecordType> findAll(QuerySpec querySpec) {
        return querySpec.apply(recordTypes.values());
    }
}
