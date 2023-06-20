package com.cloudwise.clickhouse.helper.builder;

import com.cloudwise.clickhouse.helper.SelectSqlBuilder;
import com.cloudwise.clickhouse.helper.trait.SelectSqlPart;
import com.cloudwise.clickhouse.helper.trait.SqlBuildable;

/**
 * @author timothy
 * @DateTime: 2023/6/20 11:52
 **/
public class WhereBuilder implements SqlBuildable, SelectSqlPart {
    private SelectSqlBuilder proxy;

    private OrderAndLimitBuilder orderAndLimitBuilder;

    public WhereBuilder(SelectSqlBuilder proxy, OrderAndLimitBuilder orderAndLimitBuilder) {
        this.proxy = proxy;
        this.orderAndLimitBuilder = orderAndLimitBuilder;
    }

    private String wherePart;

    public OrderAndLimitBuilder where(String where) {
        wherePart = String.format("where %s ", where);
        return orderAndLimitBuilder;
    }

    @Override
    public String build() {
        return proxy.build();
    }

    @Override
    public String part() {
        return wherePart;
    }
}
