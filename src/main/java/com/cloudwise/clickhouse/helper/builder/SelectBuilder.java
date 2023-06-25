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
        this.afterSelectBuilder = new AfterSelectBuilder(proxy, fromBuilder);
    }

    public AfterSelectBuilder select(String select) {
        append(String.format("select %s ", select));
        return afterSelectBuilder;
    }

    public AfterSelectBuilder append(String select) {
        data.append(',')
            .append(select)
            .append(' ');
        return afterSelectBuilder;
    }

    public String part() {
        return data.substring(1);
    }
}
