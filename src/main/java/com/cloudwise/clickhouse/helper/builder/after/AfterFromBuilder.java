package com.cloudwise.clickhouse.helper.builder.after;

import java.util.List;

import com.cloudwise.clickhouse.helper.SqlBuilder;
import com.cloudwise.clickhouse.helper.builder.EndpointBuilder;
import com.cloudwise.clickhouse.helper.builder.GroupByBuilder;
import com.cloudwise.clickhouse.helper.builder.JoinBuilder;
import com.cloudwise.clickhouse.helper.builder.LimitBuilder;
import com.cloudwise.clickhouse.helper.builder.OrderByBuilder;
import com.cloudwise.clickhouse.helper.builder.WhereBuilder;
import com.cloudwise.clickhouse.helper.condition.WhereCondition;
import com.cloudwise.clickhouse.helper.trait.GroupByBuildable;
import com.cloudwise.clickhouse.helper.trait.JoinBuildable;
import com.cloudwise.clickhouse.helper.trait.LimitBuildable;
import com.cloudwise.clickhouse.helper.trait.OrderByBuildable;
import com.cloudwise.clickhouse.helper.trait.SqlBuildable;
import com.cloudwise.clickhouse.helper.trait.WhereBuildable;

/**
 * @author timothy
 * @DateTime: 2023/6/21 10:34
 **/
public class AfterFromBuilder implements SqlBuildable, WhereBuildable, OrderByBuildable, LimitBuildable,
    GroupByBuildable, JoinBuildable {

    private SqlBuilder proxy;
    private WhereBuilder whereBuilder;
    private OrderByBuilder orderByBuilder;
    private LimitBuilder limitBuilder;
    private GroupByBuilder groupByBuilder;
    private JoinBuilder joinBuilder;

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
        return whereBuilder.where(whereCondition);
    }

    public LimitBuilder orderBy(String orderBy) {
        return orderByBuilder.orderBy(orderBy);
    }

    @Override
    public LimitBuilder orderByDesc(String orderBy) {
        return orderByBuilder.orderBy(orderBy);
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
    public String asSubSelect() {
        return proxy.asSubSelect();
    }

    @Override
    public AfterJoinBuilder globalJoin(String alias, String table, String on) {
        joinBuilderCheck();
        return joinBuilder.globalJoin(alias, table, on);
    }

    @Override
    public AfterJoinBuilder globalLeftJoin(String alias, String table, String on) {
        joinBuilderCheck();
        return joinBuilder.globalLeftJoin(alias, table, on);
    }

    @Override
    public AfterJoinBuilder globalRightJoin(String alias, String table, String on) {
        joinBuilderCheck();
        return joinBuilder.globalRightJoin(alias, table, on);
    }

    public void joinBuilderCheck() {
        if (this.joinBuilder != null) {
            throw new RuntimeException("joinBuilder 已经存在");
        }
        joinBuilder = new JoinBuilder(new AfterJoinBuilder(proxy, whereBuilder, orderByBuilder, limitBuilder,
            groupByBuilder));
    }

    public boolean hasJoin() {
        return joinBuilder != null;
    }

    public String getJoinTable() {
        return joinBuilder.part();
    }

}
