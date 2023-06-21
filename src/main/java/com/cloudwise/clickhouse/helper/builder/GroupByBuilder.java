package com.cloudwise.clickhouse.helper.builder;

import java.util.List;

import com.cloudwise.clickhouse.helper.JoinerUtils;
import com.cloudwise.clickhouse.helper.SqlBuilder;
import com.cloudwise.clickhouse.helper.builder.after.AfterGroupByBuilder;
import com.cloudwise.clickhouse.helper.trait.GroupByBuildable;
import com.cloudwise.clickhouse.helper.trait.SelectSqlPart;
import com.cloudwise.clickhouse.helper.trait.SqlBuildable;

/**
 * @author timothy
 * @DateTime: 2023/6/21 10:14
 **/
public class GroupByBuilder implements SqlBuildable, SelectSqlPart, GroupByBuildable {
    private SqlBuilder proxy;

    private String groupByPart;

    private AfterGroupByBuilder afterGroupByBuilder;

    public GroupByBuilder(SqlBuilder proxy, OrderByBuilder orderByBuilder, LimitBuilder limitBuilder) {
        this.proxy = proxy;
        this.afterGroupByBuilder = new AfterGroupByBuilder(proxy, orderByBuilder, limitBuilder);
    }

    public AfterGroupByBuilder groupBy(String groupBy) {
        groupByPart = String.format("group by %s ", groupBy);
        return afterGroupByBuilder;
    }

    @Override
    public AfterGroupByBuilder groupBy(List<String> groupBy) {
        groupByPart = String.format("group by %s ", JoinerUtils.PARAM_JOINER.join(groupBy));
        return afterGroupByBuilder;
    }

    @Override
    public AfterGroupByBuilder groupByHaving(String groupBy, String having) {
        groupByPart = String.format("group by %s having %s", groupBy, having);
        return afterGroupByBuilder;
    }

    @Override
    public String build() {
        return proxy.build();
    }

    @Override
    public String asJoin() {
        return proxy.asJoin();
    }

    @Override
    public String part() {
        return groupByPart;
    }
}
