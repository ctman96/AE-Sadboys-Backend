package com.ipfms.domain.model;

public class SearchResult {

    private Record record;
    private Container container;
    private Boolean checked;

    public SearchResult(Record record) {
        this.record = record;
        this.container = null;
        this.checked=false;
    }

    public SearchResult(Container container) {
        this.container = container;
        this.record = null;
        this.checked=false;
    }

    public Record getRecord() {
        return record;
    }

    public Container getContainer() {
        return container;
    }
}
