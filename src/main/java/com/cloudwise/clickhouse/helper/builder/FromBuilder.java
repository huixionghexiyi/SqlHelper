package com.cloudwise.clickhouse.helper.builder;

import com.cloudwise.clickhouse.helper.SqlBuilder;
import com.cloudwise.clickhouse.helper.builder.after.AfterFromBuilder;
import com.cloudwise.clickhouse.helper.trait.SelectSqlPart;

/**
 * from 语句构建器 不允许没有过滤条件就直接构建sql语句，所以该方法不提供 build 方法
 *
 * @author timothy
 * @DateTime: 2023/6/20 11:52
 **/
public class FromBuilder implements SelectSqlPart {
    private AfterFromBuilder afterFromBuilder;

    private String fromPart;

    public FromBuilder(SqlBuilder proxy, WhereBuilder whereBuilder, OrderByBuilder orderByBuilder,
        LimitBuilder limitBuilder, GroupByBuilder groupByBuilder) {
        this.afterFromBuilder = new AfterFromBuilder(proxy, whereBuilder, orderByBuilder, limitBuilder, groupByBuilder);
    }

    public AfterFromBuilder from(String from) {
        fromPart = String.format("from %s ", from);
        return afterFromBuilder;
    }

    @Override
    public String part() {
        return fromPart;
    }
}
