package com.cloudwise.clickhouse.helper.builder;

import com.cloudwise.clickhouse.helper.SqlBuilder;
import com.cloudwise.clickhouse.helper.trait.SqlBuildable;

/**
 * @author timothy
 * @DateTime: 2023/6/21 10:26
 **/
public class EndpointBuilder implements SqlBuildable {

    SqlBuilder proxy;

    EndpointBuilder(SqlBuilder proxy) {
        this.proxy = proxy;
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
