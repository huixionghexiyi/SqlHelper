package com.cloudwise.clickhouse.helper.condition;

/**
 * @author timothy
 * @DateTime: 2023/6/21 14:16
 **/
public class WhereCondition {

    public static WhereConditionBuilder builder() {
        return new WhereConditionBuilder();
    }
}
