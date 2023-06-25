package com.cloudwise.clickhouse.helper.builder;

import com.cloudwise.clickhouse.helper.SqlBuilder;
import com.cloudwise.clickhouse.helper.trait.OrderByBuildable;
import com.cloudwise.clickhouse.helper.trait.SqlBuildable;
import com.cloudwise.clickhouse.helper.trait.SqlPart;

/**
 * @author timothy
 * @DateTime: 2023/6/20 11:52
 **/
public class OrderByBuilder implements SqlBuildable, SqlPart, OrderByBuildable {
    private SqlBuilder proxy;
    private LimitBuilder limitBuilder;

    private String data;

    public OrderByBuilder(SqlBuilder proxy, LimitBuilder limitBuilder) {
        this.proxy = proxy;
        this.limitBuilder = limitBuilder;
    }

    @Override
    public LimitBuilder orderBy(String orderBy) {
        data = String.format("order by %s ", orderBy);
        return limitBuilder;
    }

    @Override
    public LimitBuilder orderByDesc(String orderBy) {
        data = String.format("order by %s desc", orderBy);
        return limitBuilder;
    }

    @Override
    public LimitBuilder orderByAsc(String orderBy) {
        data = String.format("order by %s asc", orderBy);
        return limitBuilder;
    }

    @Override
    public String build() {
        return proxy.build();
    }

    @Override
    public String asSubSelect() {
        return proxy.asSubSelect();
    }

    @Override
    public String part() {
        return data;
    }
}
