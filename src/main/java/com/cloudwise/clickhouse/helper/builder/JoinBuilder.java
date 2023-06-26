package com.cloudwise.clickhouse.helper.builder;

import com.cloudwise.clickhouse.helper.builder.after.AfterJoinBuilder;
import com.cloudwise.clickhouse.helper.trait.JoinBuildable;
import com.cloudwise.clickhouse.helper.trait.SqlPart;

/**
 * @author timothy
 * @DateTime: 2023/6/25 11:28
 **/
public class JoinBuilder implements JoinBuildable, SqlPart {

    private AfterJoinBuilder afterJoinBuilder;

    private String data;

    public JoinBuilder(AfterJoinBuilder afterJoinBuilder) {
        this.afterJoinBuilder = afterJoinBuilder;
    }

    @Override
    public AfterJoinBuilder globalJoin(String alias, String table, String on) {
        data = "global join " + table + " as " + alias + " on " + on;
        return afterJoinBuilder;
    }

    @Override
    public AfterJoinBuilder globalLeftJoin(String alias, String table, String on) {
        data = "global left join " + table + " on " + on + " as " + alias;
        return afterJoinBuilder;
    }

    @Override
    public AfterJoinBuilder globalRightJoin(String alias, String table, String on) {
        data = "global join join " + table + " on " + on + " as " + alias;
        return afterJoinBuilder;
    }

    @Override
    public String part() {
        return data;
    }
}
