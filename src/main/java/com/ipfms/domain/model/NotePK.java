package com.ipfms.domain.model;

import java.io.Serializable;

public class NotePK implements Serializable {

    Integer tableId;
    Integer rowId;
    Integer chunk;

    public NotePK() {
        super();
    }

    public NotePK(Integer tableId, Integer rowId, Integer chunk) {
        this.tableId = tableId;
        this.rowId = rowId;
        this.chunk = chunk;
    }

    public Integer getTableId() {
        return tableId;
    }

    public Integer getRowId() {
        return rowId;
    }

    public Integer getChunk() {
        return chunk;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        NotePK notePK = (NotePK) o;

        if (!getTableId().equals(notePK.getTableId())) return false;
        if (!getRowId().equals(notePK.getRowId())) return false;
        return getChunk().equals(notePK.getChunk());
    }

    @Override
    public int hashCode() {
        int result = getTableId().hashCode();
        result = 31 * result + getRowId().hashCode();
        result = 31 * result + getChunk().hashCode();
        return result;
    }
}
