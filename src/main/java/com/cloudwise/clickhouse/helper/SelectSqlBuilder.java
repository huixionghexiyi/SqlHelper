package com.cloudwise.clickhouse.helper;

import org.apache.commons.lang3.StringUtils;

import com.cloudwise.clickhouse.helper.builder.FromBuilder;
import com.cloudwise.clickhouse.helper.builder.LimitBuilder;
import com.cloudwise.clickhouse.helper.builder.OrderAndLimitBuilder;
import com.cloudwise.clickhouse.helper.builder.OrderByBuilder;
import com.cloudwise.clickhouse.helper.builder.SelectBuilder;
import com.cloudwise.clickhouse.helper.builder.WhereBuilder;

/**
 * @author timothy
 * @DateTime: 2023/6/20 11:51
 **/
public class SelectSqlBuilder {
    private SelectBuilder selectBuilder;
    private FromBuilder fromBuilder;
    private WhereBuilder whereBuilder;

    private OrderByBuilder orderByBuilder;

    private LimitBuilder limitBuilder;

    public SelectSqlBuilder() {
        limitBuilder = new LimitBuilder();
        orderByBuilder = new OrderByBuilder(this, limitBuilder);
        whereBuilder = new WhereBuilder(this, new OrderAndLimitBuilder(this, orderByBuilder, limitBuilder));
        fromBuilder = new FromBuilder(whereBuilder);
        selectBuilder = new SelectBuilder(fromBuilder);
    }

    public String build() {
        String sql = selectBuilder.part() + fromBuilder.part();

        if (StringUtils.isNotEmpty(whereBuilder.part())) {
            sql += whereBuilder.part();
        }
        if (StringUtils.isNotEmpty(orderByBuilder.part())) {
            sql += orderByBuilder.part();
        }
        if (StringUtils.isNotEmpty(limitBuilder.part())) {
            sql += orderByBuilder;
        }
        return sql;
    }

    public FromBuilder select(String select) {
        return selectBuilder.select(select);
    }
}
