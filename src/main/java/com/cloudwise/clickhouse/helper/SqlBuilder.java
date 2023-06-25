package com.cloudwise.clickhouse.helper;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.checkerframework.checker.units.qual.C;

import com.cloudwise.clickhouse.helper.builder.FromBuilder;
import com.cloudwise.clickhouse.helper.builder.GroupByBuilder;
import com.cloudwise.clickhouse.helper.builder.LimitBuilder;
import com.cloudwise.clickhouse.helper.builder.OrderByBuilder;
import com.cloudwise.clickhouse.helper.builder.SelectBuilder;
import com.cloudwise.clickhouse.helper.builder.WhereBuilder;
import com.cloudwise.clickhouse.helper.builder.after.AfterSelectBuilder;
import com.cloudwise.clickhouse.helper.trait.SqlBuildable;

/**
 * @author timothy
 * @DateTime: 2023/6/20 11:51
 **/
public class SqlBuilder implements SqlBuildable {
    private SelectBuilder selectBuilder;
    private FromBuilder fromBuilder;
    private WhereBuilder whereBuilder;

    private GroupByBuilder groupByBuilder;

    private OrderByBuilder orderByBuilder;

    private LimitBuilder limitBuilder;

    public SqlBuilder() {
        limitBuilder = new LimitBuilder(this);
        orderByBuilder = new OrderByBuilder(this, limitBuilder);
        groupByBuilder = new GroupByBuilder(this, orderByBuilder, limitBuilder);
        whereBuilder =
            new WhereBuilder(this, orderByBuilder, limitBuilder, groupByBuilder);
        fromBuilder = new FromBuilder(this, whereBuilder, orderByBuilder, limitBuilder, groupByBuilder);
        selectBuilder = new SelectBuilder(this, fromBuilder);
    }

    public String build() {
        String sql = selectBuilder.part() + fromBuilder.part();

        if (StringUtils.isNotEmpty(whereBuilder.part())) {
            sql += whereBuilder.part();
        }
        if (StringUtils.isNotEmpty(groupByBuilder.part())) {
            sql += groupByBuilder.part();
        }
        if (StringUtils.isNotEmpty(orderByBuilder.part())) {
            sql += orderByBuilder.part();
        }
        if (StringUtils.isNotEmpty(limitBuilder.part())) {
            sql += limitBuilder.part();
        }
        return sql;
    }

    @Override
    public String asSubSelect() {
        return String.format("( %s )", build());
    }

    public AfterSelectBuilder select(String select) {
        return selectBuilder.select(select);
    }

    public AfterSelectBuilder select(String tableAlias, List<String> select) {
        return selectBuilder.select(SelectHelper.by(tableAlias, select));
    }

    public AfterSelectBuilder select(Class<?> clazz) {
        return selectBuilder.select(SelectHelper.by(clazz));
    }

    public AfterSelectBuilder select(String tableAlias, Class<?> clazz) {
        return selectBuilder.select(SelectHelper.by(tableAlias, clazz));
    }

    public AfterSelectBuilder appendSelect(String tableAlias, Class<?> clazz) {
        return selectBuilder.append(SelectHelper.by(tableAlias, clazz));
    }
}
