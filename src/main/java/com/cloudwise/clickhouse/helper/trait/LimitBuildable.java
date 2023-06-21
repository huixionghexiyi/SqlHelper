package com.cloudwise.clickhouse.helper.trait;

import com.cloudwise.clickhouse.helper.builder.EndpointBuilder;

/**
 * @author timothy
 * @DateTime: 2023/6/21 14:14
 **/
public interface LimitBuildable {

    EndpointBuilder limit(String limit);
}
