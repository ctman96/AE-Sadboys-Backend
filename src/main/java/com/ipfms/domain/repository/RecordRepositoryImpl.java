package com.ipfms.domain.repository;

import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Component;
import com.ipfms.domain.model.Record;
import io.katharsis.queryspec.QuerySpec;
import io.katharsis.repository.ResourceRepositoryBase;
import io.katharsis.resource.list.ResourceList;

/**
 * Created by Cody on 2017-10-21.
 */

@Component
public class RecordRepositoryImpl extends ResourceRepositoryBase<Record, Long> {
    private Map<Long, Record> records = new HashMap<>();
    public RecordRepositoryImpl() {
        super(Record.class);
    }
    @Override
    public synchronized ResourceList<Record> findAll(QuerySpec querySpec) {
        return querySpec.apply(records.values());
    }
}
