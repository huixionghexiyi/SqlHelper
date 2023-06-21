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
 * @DateTime: 2023/6/21 10:34
 **/
public class AfterFromBuilder implements SqlBuildable, WhereBuildable, OrderByBuildable, LimitBuildable,
    GroupByBuildable {

    private SqlBuilder proxy;
    private WhereBuilder whereBuilder;
    private OrderByBuilder orderByBuilder;
    private LimitBuilder limitBuilder;
    private GroupByBuilder groupByBuilder;

    public AfterFromBuilder(SqlBuilder proxy, WhereBuilder whereBuilder, OrderByBuilder orderByBuilder,
        LimitBuilder limitBuilder,
        GroupByBuilder groupByBuilder) {
        this.proxy = proxy;
        this.whereBuilder = whereBuilder;
        this.orderByBuilder = orderByBuilder;
        this.limitBuilder = limitBuilder;
        this.groupByBuilder = groupByBuilder;
    }

    public AfterWhereBuilder where(String where) {
        return whereBuilder.where(where);
    }

    @Override
    public AfterWhereBuilder where(WhereCondition whereCondition) {
        return null;
    }

    public LimitBuilder orderBy(String orderBy) {
        return orderByBuilder.orderBy(orderBy);
    }

    @Override
    public LimitBuilder orderByDesc(String orderBy) {
        return null;
    }

    @Override
    public LimitBuilder orderByAsc(String orderBy) {
        return orderByBuilder.orderBy(orderBy);
    }

    public EndpointBuilder limit(String limit) {
        return limitBuilder.limit(limit);
    }

    public AfterGroupByBuilder groupBy(String groupBy) {
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
    public String build() {
        return proxy.build();
    }

    @Override
    public String asJoin() {
        return proxy.asJoin();
    }
}
