package com.cloudwise.clickhouse.helper.builder;

/**
 * @author timothy
 * @DateTime: 2023/6/20 11:13
 **/
public class SelectBuilder {

    private String selectPart;
    private FromBuilder fromBuilder;

    public SelectBuilder(FromBuilder fromBuilder) {
        this.fromBuilder = fromBuilder;
    }

    public FromBuilder join(String join) {
        return fromBuilder;
    }

    public FromBuilder select(String select) {
        this.selectPart = String.format("select %s ", select);
        return fromBuilder;
    }

    public String part() {
        return selectPart;
    }
}
