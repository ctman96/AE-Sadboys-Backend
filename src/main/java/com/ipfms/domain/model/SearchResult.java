package com.ipfms.domain.model;

public class SearchResult {

    private Record record;
    private Container container;

    public SearchResult(Record record) {
        this.record = record;
        this.container = null;
    }

    public SearchResult(Container container) {
        this.container = container;
        this.record = null;
    }

    public Record getRecord() {
        return record;
    }

    public Container getContainer() {
        return container;
    }
}
