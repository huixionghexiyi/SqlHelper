package com.cloudwise.clickhouse.helper.builder;

import com.cloudwise.clickhouse.helper.SqlBuilder;
import com.cloudwise.clickhouse.helper.builder.after.AfterWhereBuilder;
import com.cloudwise.clickhouse.helper.trait.SelectSqlPart;
import com.cloudwise.clickhouse.helper.trait.SqlBuildable;

/**
 * @author timothy
 * @DateTime: 2023/6/20 11:52
 **/
public class WhereBuilder implements SqlBuildable, SelectSqlPart {
    private SqlBuilder proxy;

    private AfterWhereBuilder afterWhereBuilder;

    public WhereBuilder(SqlBuilder proxy, OrderByBuilder orderByBuilder, LimitBuilder limitBuilder,
        GroupByBuilder groupByBuilder) {
        this.proxy = proxy;
        this.afterWhereBuilder = new AfterWhereBuilder(proxy, orderByBuilder, limitBuilder, groupByBuilder);
    }

    private String wherePart;

    public AfterWhereBuilder where(String where) {
        wherePart = String.format("where %s ", where);
        return afterWhereBuilder;
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
        return wherePart;
    }
}
