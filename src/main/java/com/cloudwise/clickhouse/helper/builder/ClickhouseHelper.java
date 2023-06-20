package com.cloudwise.clickhouse.helper.builder;

import com.cloudwise.clickhouse.helper.SelectSqlBuilder;

/**
 * @author timothy
 * @DateTime: 2023/6/20 11:03
 **/
public class ClickhouseHelper {

    public static SelectSqlBuilder selectBuilder() {
        return new SelectSqlBuilder();
    }

}
