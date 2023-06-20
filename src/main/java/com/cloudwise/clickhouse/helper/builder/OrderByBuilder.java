package com.cloudwise.clickhouse.helper.builder;

import com.cloudwise.clickhouse.helper.SelectSqlBuilder;
import com.cloudwise.clickhouse.helper.trait.SelectSqlPart;
import com.cloudwise.clickhouse.helper.trait.SqlBuildable;

/**
 * @author timothy
 * @DateTime: 2023/6/20 11:52
 **/
public class OrderByBuilder implements SqlBuildable, SelectSqlPart {
    private SelectSqlBuilder proxy;
    private LimitBuilder limitBuilder;

    private String orderByPart;

    public OrderByBuilder(SelectSqlBuilder proxy, LimitBuilder limitBuilder) {
        this.proxy = proxy;
        this.limitBuilder = limitBuilder;
    }

    public LimitBuilder orderBy(String orderBy) {
        return limitBuilder;
    }

    @Override
    public String build() {
        return proxy.build();
    }

    @Override
    public String part() {
        return orderByPart;
    }
}
