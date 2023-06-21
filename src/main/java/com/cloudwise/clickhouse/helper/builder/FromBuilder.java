package com.cloudwise.clickhouse.helper.builder;

import com.cloudwise.clickhouse.helper.SqlBuilder;
import com.cloudwise.clickhouse.helper.builder.after.AfterFromBuilder;
import com.cloudwise.clickhouse.helper.trait.FromBuildable;
import com.cloudwise.clickhouse.helper.trait.SqlPart;

/**
 * from 语句构建器 不允许没有过滤条件就直接构建sql语句，所以该方法不提供 build 方法
 *
 * @author timothy
 * @DateTime: 2023/6/20 11:52
 **/
public class FromBuilder implements SqlPart, FromBuildable {
    private AfterFromBuilder afterFromBuilder;

    private String data;

    public FromBuilder(SqlBuilder proxy, WhereBuilder whereBuilder, OrderByBuilder orderByBuilder,
        LimitBuilder limitBuilder, GroupByBuilder groupByBuilder) {
        this.afterFromBuilder = new AfterFromBuilder(proxy, whereBuilder, orderByBuilder, limitBuilder, groupByBuilder);
    }

    @Override
    public AfterFromBuilder from(String from) {
        data = String.format("from %s ", from);
        return afterFromBuilder;
    }

    @Override

    public AfterFromBuilder from(String tableAlias, String from) {
        return from(String.format(" from %s AS %s ", from, tableAlias));
    }

    @Override
    public String part() {
        return data;
    }
}
