package com.cloudwise.clickhouse.helper.builder;

import com.cloudwise.clickhouse.helper.SelectHelper;

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

    public FromBuilder select(String select) {
        this.selectPart = String.format("select %s ", select);
        return fromBuilder;
    }

    public FromBuilder select(Class clazz) {
        String selectParams = SelectHelper.buildSelectParamByClass(clazz);
        this.selectPart = String.format("select %s ", selectParams);
        return fromBuilder;
    }

    public String part() {
        return selectPart;
    }
}
