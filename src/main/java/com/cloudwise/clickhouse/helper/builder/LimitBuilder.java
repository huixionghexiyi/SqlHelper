package com.cloudwise.clickhouse.helper.builder;

import com.cloudwise.clickhouse.helper.SelectSqlBuilder;
import com.cloudwise.clickhouse.helper.trait.SelectSqlPart;
import com.cloudwise.clickhouse.helper.trait.SqlBuildable;

/**
 * @author timothy
 * @DateTime: 2023/6/20 11:52
 **/
public class LimitBuilder implements SqlBuildable, SelectSqlPart {

    private SelectSqlBuilder proxy;
    private String limitPart;

    public Endpoint limit(String limit) {
        limitPart = String.format("limit %s", limit);
        return new Endpoint(this);
    }

    @Override
    public String build() {
        return proxy.build();
    }

    @Override
    public String part() {
        return limitPart;
    }

    class Endpoint implements SqlBuildable {
        LimitBuilder limitBuilder;

        Endpoint(LimitBuilder limitBuilder) {
            this.limitBuilder = limitBuilder;
        }

        @Override
        public String build() {
            return limitBuilder.build();
        }
    }
}
