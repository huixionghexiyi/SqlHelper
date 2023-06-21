package com.cloudwise.clickhouse.helper;

import com.google.common.base.Joiner;

import com.cloudwise.clickhouse.helper.SqlBuilder;

/**
 * @author timothy
 * @DateTime: 2023/6/20 11:03
 **/
public class ClickhouseHelper {



    public static SqlBuilder selectBuilder() {
        return new SqlBuilder();
    }

}
