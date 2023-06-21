package com.cloudwise.clickhouse.helper.trait;

import java.util.List;

import com.cloudwise.clickhouse.helper.builder.LimitBuilder;

/**
 * @author timothy
 * @DateTime: 2023/6/21 14:14
 **/
public interface OrderByBuildable {

    LimitBuilder orderBy(String orderBy);

    LimitBuilder orderByDesc(String orderBy);

    LimitBuilder orderByAsc(String orderBy);
}
