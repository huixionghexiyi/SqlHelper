package com.cloudwise.clickhouse.helper.builder;

import com.cloudwise.clickhouse.helper.SelectSqlBuilder;
import com.cloudwise.clickhouse.helper.builder.LimitBuilder.Endpoint;
import com.cloudwise.clickhouse.helper.trait.SqlBuildable;

/**
 * @author timothy
 * @DateTime: 2023/6/20 14:56
 **/
public class OrderAndLimitBuilder implements SqlBuildable {

    private SelectSqlBuilder proxy;
    private OrderByBuilder orderByBuilder;
    private LimitBuilder limitBuilder;

    public OrderAndLimitBuilder(SelectSqlBuilder proxy, OrderByBuilder orderByBuilder, LimitBuilder limitBuilder) {
        this.proxy = proxy;
        this.orderByBuilder = orderByBuilder;
        this.limitBuilder = limitBuilder;
    }

    public LimitBuilder orderBy(String orderBy) {
        return orderByBuilder.orderBy(orderBy);
    }

    public Endpoint limit(String limit) {
        return limitBuilder.limit(limit);
    }

    @Override
    public String build() {
        return proxy.build();
    }
}
