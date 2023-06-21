package com.cloudwise.clickhouse.helper.trait;

import java.util.List;

import com.cloudwise.clickhouse.helper.builder.after.AfterGroupByBuilder;

/**
 * @author timothy
 * @DateTime: 2023/6/21 14:14
 **/
public interface GroupByBuildable {
    AfterGroupByBuilder groupBy(String groupBy);

    AfterGroupByBuilder groupBy(List<String> groupBy);

    AfterGroupByBuilder groupByHaving(String groupBy, String having);
}
