package com.cloudwise.clickhouse.helper;

import com.google.common.base.Joiner;

/**
 * @author timothy
 * @DateTime: 2023/6/21 11:59
 **/
public class JoinerUtils {
    public static final Joiner PARAM_JOINER = Joiner.on(",");
    public static final Joiner UNDERLINE_JOINER = Joiner.on("_");

    public static final Joiner WHERE_CONDITION_JOINER = Joiner.on(" and ");
}
