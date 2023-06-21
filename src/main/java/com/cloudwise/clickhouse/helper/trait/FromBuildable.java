package com.cloudwise.clickhouse.helper.trait;

import com.cloudwise.clickhouse.helper.builder.after.AfterFromBuilder;

/**
 * @author timothy
 * @DateTime: 2023/6/21 16:28
 **/
public interface FromBuildable {
    AfterFromBuilder from(String from);

    AfterFromBuilder from(String tableAlias, String from);
}
