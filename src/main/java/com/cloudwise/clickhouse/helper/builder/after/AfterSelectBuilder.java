package com.cloudwise.clickhouse.helper.builder.after;

import java.util.List;

import com.cloudwise.clickhouse.helper.SelectHelper;
import com.cloudwise.clickhouse.helper.builder.FromBuilder;
import com.cloudwise.clickhouse.helper.builder.SelectBuilder;
import com.cloudwise.clickhouse.helper.trait.FromBuildable;

/**
 * @author timothy
 * @DateTime: 2023/6/21 10:34
 **/
public class AfterSelectBuilder implements FromBuildable {

    private FromBuilder fromBuilder;

    private SelectBuilder selectBuilder;

    public AfterSelectBuilder(FromBuilder fromBuilder, SelectBuilder selectBuilder) {
        this.selectBuilder = selectBuilder;
        this.fromBuilder = fromBuilder;
    }

    public AfterSelectBuilder appendSelect(String select) {
        return selectBuilder.select(select);
    }

    public AfterSelectBuilder appendSelect(String tableAlias, List<String> select) {
        return selectBuilder.select(SelectHelper.by(tableAlias, select));
    }

    public AfterSelectBuilder appendSelect(Class<?> clazz) {
        return selectBuilder.select(SelectHelper.by(clazz));
    }

    public AfterSelectBuilder appendSelect(String tableAlias, Class<?> clazz) {
        return selectBuilder.select(SelectHelper.by(tableAlias, clazz));
    }

    @Override
    public AfterFromBuilder from(String from) {
        return fromBuilder.from(from);
    }

    @Override
    public AfterFromBuilder from(String tableAlias, String from) {
        return fromBuilder.from(tableAlias, from);
    }
}
