package com.cloudwise.clickhouse.helper.builder;

import com.cloudwise.clickhouse.helper.SqlBuilder;
import com.cloudwise.clickhouse.helper.builder.after.AfterSelectBuilder;

/**
 * @author timothy
 * @DateTime: 2023/6/20 11:13
 **/
public class SelectBuilder {

    private StringBuilder data;

    private AfterSelectBuilder afterSelectBuilder;

    public SelectBuilder(SqlBuilder proxy, FromBuilder fromBuilder) {
        this.data = new StringBuilder();
        this.afterSelectBuilder = new AfterSelectBuilder(fromBuilder, this);
    }

    public AfterSelectBuilder select(String select) {
        data.append(',')
            .append(select)
            .append(' ');
        return afterSelectBuilder;
    }

    public AfterSelectBuilder select() {
        return afterSelectBuilder;
    }

    public String part() {
        return data.replace(0, 1, "select ").toString();
    }
}
