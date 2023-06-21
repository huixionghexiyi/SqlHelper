package com.cloudwise.clickhouse.helper.trait;

import com.cloudwise.clickhouse.helper.builder.after.AfterWhereBuilder;
import com.cloudwise.clickhouse.helper.condition.WhereCondition;

/**
 * @author timothy
 * @DateTime: 2023/6/21 14:13
 **/
public interface WhereBuildable {
    AfterWhereBuilder where(String where);

    AfterWhereBuilder where(WhereCondition whereCondition);
}
