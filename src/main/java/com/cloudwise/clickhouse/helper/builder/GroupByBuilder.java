package com.cloudwise.clickhouse.helper.builder;

import java.util.List;

import com.cloudwise.clickhouse.helper.JoinerUtils;
import com.cloudwise.clickhouse.helper.SqlBuilder;
import com.cloudwise.clickhouse.helper.builder.after.AfterGroupByBuilder;
import com.cloudwise.clickhouse.helper.trait.GroupByBuildable;
import com.cloudwise.clickhouse.helper.trait.SqlBuildable;
import com.cloudwise.clickhouse.helper.trait.SqlPart;

/**
 * @author timothy
 * @DateTime: 2023/6/21 10:14
 **/
public class GroupByBuilder implements SqlBuildable, SqlPart, GroupByBuildable {
    private SqlBuilder proxy;

    private String data;

    private AfterGroupByBuilder afterGroupByBuilder;

    public GroupByBuilder(SqlBuilder proxy, OrderByBuilder orderByBuilder, LimitBuilder limitBuilder) {
        this.proxy = proxy;
        this.afterGroupByBuilder = new AfterGroupByBuilder(proxy, orderByBuilder, limitBuilder);
    }

    public AfterGroupByBuilder groupBy(String groupBy) {
        data = String.format("group by %s ", groupBy);
        return afterGroupByBuilder;
    }

    @Override
    public AfterGroupByBuilder groupBy(String... groupBy) {
        return groupBy(JoinerUtils.PARAM_JOINER.join(groupBy));
    }

    @Override
    public AfterGroupByBuilder groupBy(List<String> groupBy) {
        return groupBy(JoinerUtils.PARAM_JOINER.join(groupBy));
    }

    @Override
    public AfterGroupByBuilder groupByHaving(String groupBy, String having) {
        return groupBy(String.format("%s having %s ", groupBy, having));
    }

    @Override
    public String build() {
        return proxy.build();
    }

    @Override
    public String asSubSelect() {
        return proxy.asSubSelect();
    }

    @Override
    public String part() {
        return data;
    }
}
