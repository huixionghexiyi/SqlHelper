package com.cloudwise.clickhouse.helper.trait;

import com.cloudwise.clickhouse.helper.builder.after.AfterJoinBuilder;

/**
 * @author timothy
 * @DateTime: 2023/6/25 11:25
 **/
public interface JoinBuildable {

    AfterJoinBuilder globalJoin(String alias, String table, String on);

    AfterJoinBuilder globalLeftJoin(String alias, String table, String on);

    AfterJoinBuilder globalRightJoin(String alias, String table, String on);
}
