package com.cloudwise.clickhouse.helper;

import com.google.common.base.Joiner;

import com.cloudwise.clickhouse.helper.SqlBuilder;

/**
 * @author timothy
 * @DateTime: 2023/6/20 11:03
 **/
public class ClickhouseHelper {

    public static final Joiner PARAM_JOINER = Joiner.on(",");
    public static final Joiner UNDERLINE_JOINER = Joiner.on("_");

    public static SqlBuilder selectBuilder() {
        return new SqlBuilder();
    }

}
