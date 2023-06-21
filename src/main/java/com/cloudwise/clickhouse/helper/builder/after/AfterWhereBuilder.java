package com.cloudwise.clickhouse.helper.builder.after;

import java.util.List;

import com.cloudwise.clickhouse.helper.JoinerUtils;
import com.cloudwise.clickhouse.helper.SqlBuilder;
import com.cloudwise.clickhouse.helper.builder.EndpointBuilder;
import com.cloudwise.clickhouse.helper.builder.GroupByBuilder;
import com.cloudwise.clickhouse.helper.builder.LimitBuilder;
import com.cloudwise.clickhouse.helper.builder.OrderByBuilder;
import com.cloudwise.clickhouse.helper.trait.GroupByBuildable;
import com.cloudwise.clickhouse.helper.trait.LimitBuildable;
import com.cloudwise.clickhouse.helper.trait.OrderByBuildable;
import com.cloudwise.clickhouse.helper.trait.SqlBuildable;

/**
 * @author timothy
 * @DateTime: 2023/6/20 14:56
 **/
public class AfterWhereBuilder implements SqlBuildable, OrderByBuildable, LimitBuildable, GroupByBuildable {

    private SqlBuilder proxy;
    private OrderByBuilder orderByBuilder;
    private LimitBuilder limitBuilder;

    private GroupByBuilder groupByBuilder;

    public AfterWhereBuilder(SqlBuilder proxy, OrderByBuilder orderByBuilder, LimitBuilder limitBuilder,
        GroupByBuilder groupByBuilder) {
        this.proxy = proxy;
        this.orderByBuilder = orderByBuilder;
        this.limitBuilder = limitBuilder;
        this.groupByBuilder = groupByBuilder;
    }

    public LimitBuilder orderBy(String orderBy) {
        return orderByBuilder.orderBy(orderBy);
    }

    public LimitBuilder orderByDesc(String desc) {
        return orderByBuilder.orderBy(desc + " desc");
    }

    public LimitBuilder orderByAsc(String asc) {
        return orderByBuilder.orderBy(asc + " asc");
    }

    public EndpointBuilder limit(String limit) {
        return limitBuilder.limit(limit);
    }

    public AfterGroupByBuilder groupBy(String groupBy) {
        return groupByBuilder.groupBy(groupBy);
    }

    public AfterGroupByBuilder groupBy(List<String> groupBy) {
        String join = JoinerUtils.PARAM_JOINER.join(groupBy);
        return groupByBuilder.groupBy(join);
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
