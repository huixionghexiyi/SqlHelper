package com.cloudwise.clickhouse.helper.trait;

/**
 * sql的部分片段，用于拼接sql语句
 * @author timothy
 * @DateTime: 2023/6/20 14:35
 **/
public interface SqlPart {
    String part();
}
