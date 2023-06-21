package com.cloudwise.clickhouse.helper.trait;

/**
 * 允许进行 sql构建的节点
 * @author timothy
 * @DateTime: 2023/6/20 14:10
 **/
public interface SqlBuildable {

    String build();

    String asJoin();
}
