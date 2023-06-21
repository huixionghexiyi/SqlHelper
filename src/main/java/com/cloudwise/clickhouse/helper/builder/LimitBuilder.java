package com.cloudwise.clickhouse.helper.builder;

import com.cloudwise.clickhouse.helper.SqlBuilder;
import com.cloudwise.clickhouse.helper.trait.SelectSqlPart;
import com.cloudwise.clickhouse.helper.trait.SqlBuildable;

/**
 * @author timothy
 * @DateTime: 2023/6/20 11:52
 **/
public class LimitBuilder implements SqlBuildable, SelectSqlPart {

    private SqlBuilder proxy;
    private String limitPart;

    public LimitBuilder(SqlBuilder proxy) {
        this.proxy = proxy;
    }

    public EndpointBuilder limit(String limit) {
        limitPart = String.format("limit %s", limit);
        return new EndpointBuilder(proxy);
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
        return limitPart;
    }
}
