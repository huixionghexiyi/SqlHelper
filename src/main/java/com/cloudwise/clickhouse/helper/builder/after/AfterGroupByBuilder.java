package com.cloudwise.clickhouse.helper.builder.after;

import com.cloudwise.clickhouse.helper.SqlBuilder;
import com.cloudwise.clickhouse.helper.builder.EndpointBuilder;
import com.cloudwise.clickhouse.helper.builder.LimitBuilder;
import com.cloudwise.clickhouse.helper.builder.OrderByBuilder;
import com.cloudwise.clickhouse.helper.trait.LimitBuildable;
import com.cloudwise.clickhouse.helper.trait.OrderByBuildable;
import com.cloudwise.clickhouse.helper.trait.SqlBuildable;

/**
 * @author timothy
 * @DateTime: 2023/6/21 10:20
 **/
public class AfterGroupByBuilder implements SqlBuildable, OrderByBuildable, LimitBuildable {
    private SqlBuilder proxy;
    private OrderByBuilder orderByBuilder;
    private LimitBuilder limitBuilder;

    public AfterGroupByBuilder(SqlBuilder proxy, OrderByBuilder orderByBuilder, LimitBuilder limitBuilder) {
        this.proxy = proxy;
        this.orderByBuilder = orderByBuilder;
        this.limitBuilder = limitBuilder;
    }

    @Override
    public LimitBuilder orderBy(String orderBy) {
        return orderByBuilder.orderBy(orderBy);
    }

    @Override
    public LimitBuilder orderByDesc(String desc) {
        return orderByBuilder.orderByDesc(desc);
    }

    @Override
    public LimitBuilder orderByAsc(String asc) {
        return orderByBuilder.orderBy(asc);
    }

    @Override
    public EndpointBuilder limit(String limit) {
        return limitBuilder.limit(limit);
    }

    @Override
    public String build() {
        return proxy.build();
    }

    @Override
    public String asSubSelect() {
        return proxy.asSubSelect();
    }
}
