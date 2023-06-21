package com.cloudwise.clickhouse.helper.builder.after;

import com.cloudwise.clickhouse.helper.SqlBuilder;
import com.cloudwise.clickhouse.helper.builder.EndpointBuilder;
import com.cloudwise.clickhouse.helper.builder.LimitBuilder;
import com.cloudwise.clickhouse.helper.builder.OrderByBuilder;
import com.cloudwise.clickhouse.helper.trait.SqlBuildable;

/**
 * @author timothy
 * @DateTime: 2023/6/21 10:20
 **/
public class AfterGroupByBuilder implements SqlBuildable {
    private SqlBuilder proxy;
    private OrderByBuilder orderByBuilder;
    private LimitBuilder limitBuilder;

    public AfterGroupByBuilder(SqlBuilder proxy, OrderByBuilder orderByBuilder, LimitBuilder limitBuilder) {
        this.proxy = proxy;
        this.orderByBuilder = orderByBuilder;
        this.limitBuilder = limitBuilder;
    }

    public LimitBuilder orderBy(String orderBy) {
        return orderByBuilder.orderBy(orderBy);
    }

    public EndpointBuilder limit(String limit) {
        return limitBuilder.limit(limit);
    }

    @Override
    public String build() {
        return proxy.build();
    }

    @Override
    public String asJoin() {
        return proxy.asJoin();
    }
}
