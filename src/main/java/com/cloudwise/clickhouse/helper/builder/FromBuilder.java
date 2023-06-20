package com.cloudwise.clickhouse.helper.builder;

import com.cloudwise.clickhouse.helper.trait.SelectSqlPart;

/**
 * from 语句构建器
 * 不允许没有过滤条件就直接构建sql语句，所以该方法不提供 build 方法
 * @author timothy
 * @DateTime: 2023/6/20 11:52
 **/
public class FromBuilder implements SelectSqlPart {
    private WhereBuilder whereBuilder;

    private String fromPart;

    public FromBuilder(WhereBuilder whereBuilder) {
        this.whereBuilder = whereBuilder;
    }

    public WhereBuilder from(String from) {
        fromPart = String.format("from %s ", from);
        return whereBuilder;
    }

    @Override
    public String part() {
        return fromPart;
    }
}
