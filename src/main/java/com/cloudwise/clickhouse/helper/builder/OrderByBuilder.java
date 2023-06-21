package com.cloudwise.clickhouse.helper.builder;

import com.cloudwise.clickhouse.helper.SqlBuilder;
import com.cloudwise.clickhouse.helper.trait.OrderByBuildable;
import com.cloudwise.clickhouse.helper.trait.SelectSqlPart;
import com.cloudwise.clickhouse.helper.trait.SqlBuildable;

/**
 * @author timothy
 * @DateTime: 2023/6/20 11:52
 **/
public class OrderByBuilder implements SqlBuildable, SelectSqlPart, OrderByBuildable {
    private SqlBuilder proxy;
    private LimitBuilder limitBuilder;

    private String orderByPart;

    public OrderByBuilder(SqlBuilder proxy, LimitBuilder limitBuilder) {
        this.proxy = proxy;
        this.limitBuilder = limitBuilder;
    }

    public LimitBuilder orderBy(String orderBy) {
        orderByPart = String.format("order by %s ", orderBy);
        return limitBuilder;
    }

    @Override
    public LimitBuilder orderByDesc(String orderBy) {
        orderByPart = String.format("order by %s desc", orderBy);
        return limitBuilder;
    }

    @Override
    public LimitBuilder orderByAsc(String orderBy) {
        orderByPart = String.format("order by %s asc", orderBy);
        return limitBuilder;
    }

    @Override
    public String build() {
        return proxy.build();
    }

    @Override
    public String asJoin() {
        return proxy.asJoin();
    }

    @Override
    public String part() {
        return orderByPart;
    }
}
