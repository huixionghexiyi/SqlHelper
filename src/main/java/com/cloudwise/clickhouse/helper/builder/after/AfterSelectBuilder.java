package com.cloudwise.clickhouse.helper.builder.after;

import java.util.List;

import com.cloudwise.clickhouse.helper.SqlBuilder;
import com.cloudwise.clickhouse.helper.builder.FromBuilder;
import com.cloudwise.clickhouse.helper.trait.FromBuildable;

/**
 * @author timothy
 * @DateTime: 2023/6/21 10:34
 **/
public class AfterSelectBuilder implements FromBuildable {

    private SqlBuilder proxy;

    private FromBuilder fromBuilder;

    public AfterSelectBuilder(SqlBuilder proxy, FromBuilder fromBuilder) {
        this.proxy = proxy;
        this.fromBuilder = fromBuilder;
    }

    public AfterSelectBuilder appendSelect(String select) {
        return proxy.select(select);
    }

    public AfterSelectBuilder appendSelect(String tableAlias, List<String> select) {
        return proxy.select(tableAlias, select);
    }

    public AfterSelectBuilder appendSelect(Class<?> clazz) {
        return proxy.select(clazz);
    }

    public AfterSelectBuilder appendSelect(String tableAlias, Class<?> clazz) {
        return proxy.select(tableAlias, clazz);
    }

    @Override
    public AfterFromBuilder from(String from) {
        return fromBuilder.from(from);
    }

    @Override
    public AfterFromBuilder from(String tableAlias, String from) {
        return fromBuilder.from(tableAlias, from);
    }
}
