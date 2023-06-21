package com.cloudwise.clickhouse.helper.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

/**
 * @author timothy
 * @DateTime: 2023/6/21 16:06
 **/
@Documented
@Target(ElementType.TYPE)
public @interface ClickhouseTable {

    String db() default "";

    String table() default "";

    /**
     * 表的别名
     *
     * @return
     */
    String alias() default "";
}
