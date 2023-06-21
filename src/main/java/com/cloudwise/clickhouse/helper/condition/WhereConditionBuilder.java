package com.cloudwise.clickhouse.helper.condition;

/**
 * @author timothy
 * @DateTime: 2023/6/21 14:18
 **/
public class WhereConditionBuilder {

    public WhereConditionBuilder and() {
        return this;
    }

    public String build() {
        return "";
    }
}
