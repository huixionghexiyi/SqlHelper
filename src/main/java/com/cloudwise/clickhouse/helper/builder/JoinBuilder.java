package com.cloudwise.clickhouse.helper.builder;

import com.cloudwise.clickhouse.helper.builder.after.AfterJoinBuilder;
import com.cloudwise.clickhouse.helper.trait.JoinBuildable;

/**
 * @author timothy
 * @DateTime: 2023/6/25 11:28
 **/
public class JoinBuilder implements JoinBuildable {

    private AfterJoinBuilder afterJoinBuilder;

    @Override
    public AfterJoinBuilder globalJoin(String alias, String table, String on) {
        return null;
    }

    @Override
    public AfterJoinBuilder globalLeftJoin(String alias, String table, String on) {
        return null;
    }

    @Override
    public AfterJoinBuilder globalRightJoin(String alias, String table, String on) {
        return null;
    }
}
