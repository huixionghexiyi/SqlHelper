package com.cloudwise.clickhouse.helper.builder.after;

import java.util.List;

import com.cloudwise.clickhouse.helper.SqlBuilder;
import com.cloudwise.clickhouse.helper.builder.EndpointBuilder;
import com.cloudwise.clickhouse.helper.builder.GroupByBuilder;
import com.cloudwise.clickhouse.helper.builder.LimitBuilder;
import com.cloudwise.clickhouse.helper.builder.OrderByBuilder;
import com.cloudwise.clickhouse.helper.builder.WhereBuilder;
import com.cloudwise.clickhouse.helper.condition.WhereCondition;
import com.cloudwise.clickhouse.helper.trait.GroupByBuildable;
import com.cloudwise.clickhouse.helper.trait.LimitBuildable;
import com.cloudwise.clickhouse.helper.trait.OrderByBuildable;
import com.cloudwise.clickhouse.helper.trait.SqlBuildable;
import com.cloudwise.clickhouse.helper.trait.WhereBuildable;

/**
 * @author timothy
 * @DateTime: 2023/6/25 11:28
 **/
public class AfterJoinBuilder
    implements SqlBuildable, WhereBuildable, OrderByBuildable, LimitBuildable, GroupByBuildable {

    private SqlBuilder proxy;

    private WhereBuilder whereBuilder;
    private OrderByBuilder orderByBuilder;
    private LimitBuilder limitBuilder;
    private GroupByBuilder groupByBuilder;

    public AfterJoinBuilder(SqlBuilder proxy, WhereBuilder whereBuilder, OrderByBuilder orderByBuilder,
        LimitBuilder limitBuilder, GroupByBuilder groupByBuilder) {
        this.proxy = proxy;
        this.whereBuilder = whereBuilder;
        this.orderByBuilder = orderByBuilder;
        this.limitBuilder = limitBuilder;
        this.groupByBuilder = groupByBuilder;
    }

    @Override
    public AfterGroupByBuilder groupBy(String groupBy) {
        return groupByBuilder.groupBy(groupBy);
    }

    @Override
    public AfterGroupByBuilder groupBy(String... groupBy) {
        return groupByBuilder.groupBy(groupBy);
    }

    @Override
    public AfterGroupByBuilder groupBy(List<String> groupBy) {
        return groupByBuilder.groupBy(groupBy);
    }

    @Override
    public AfterGroupByBuilder groupByHaving(String groupBy, String having) {
        return groupByBuilder.groupByHaving(groupBy, having);
    }

    @Override
    public EndpointBuilder limit(String limit) {
        return limitBuilder.limit(limit);
    }

    @Override
    public LimitBuilder orderBy(String orderBy) {
        return orderByBuilder.orderBy(orderBy);
    }

    @Override
    public LimitBuilder orderByDesc(String orderBy) {
        return orderByBuilder.orderByDesc(orderBy);
    }

    @Override
    public LimitBuilder orderByAsc(String orderBy) {
        return orderByBuilder.orderByAsc(orderBy);
    }

    @Override
    public AfterWhereBuilder where(String where) {
        return whereBuilder.where(where);
    }

    @Override
    public AfterWhereBuilder where(WhereCondition whereCondition) {
        return whereBuilder.where(whereCondition);
    }

    @Override
    public String build() {
        return proxy.build();
    }

    @Override
    public String asSubSelect() {
        return proxy.asSubSelect();
    }
}
