package com.cloudwise.clickhouse.helper.builder;

import lombok.Data;

import com.cloudwise.clickhouse.helper.SqlBuilder;
import com.cloudwise.clickhouse.helper.builder.after.AfterWhereBuilder;
import com.cloudwise.clickhouse.helper.condition.WhereCondition;
import com.cloudwise.clickhouse.helper.trait.SqlBuildable;
import com.cloudwise.clickhouse.helper.trait.SqlPart;
import com.cloudwise.clickhouse.helper.trait.WhereBuildable;

/**
 * @author timothy
 * @DateTime: 2023/6/20 11:52
 **/
@Data
public class WhereBuilder implements SqlBuildable, SqlPart, WhereBuildable {

    private SqlBuilder proxy;

    private AfterWhereBuilder afterWhereBuilder;

    private String data;

    public WhereBuilder(SqlBuilder proxy, OrderByBuilder orderByBuilder, LimitBuilder limitBuilder,
        GroupByBuilder groupByBuilder) {
        this.proxy = proxy;
        this.afterWhereBuilder = new AfterWhereBuilder(proxy, orderByBuilder, limitBuilder, groupByBuilder);
    }

    public AfterWhereBuilder where(String where) {
        data = String.format("where %s ", where);
        return afterWhereBuilder;
    }

    @Override
    public AfterWhereBuilder where(WhereCondition whereCondition) {
        data = String.format("where %s ", whereCondition.getCondition());
        return afterWhereBuilder;
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
