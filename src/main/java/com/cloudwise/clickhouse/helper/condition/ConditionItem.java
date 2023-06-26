package com.cloudwise.clickhouse.helper.condition;

import java.util.List;

import com.cloudwise.clickhouse.helper.JoinerUtils;

/**
 * @author timothy
 * @DateTime: 2023/6/21 19:39
 **/
public class ConditionItem {

    public static String IN = "in";
    public static String NOT_IN = "not in";
    public static String EQUALS = "=";
    public static String NOT_EQUALS = "!=";
    public static String GREATER_THAN = ">";
    public static String LESS_THAN = "<";
    public static String GREATER_THAN_OR_EQUALS = ">=";
    public static String LESS_THAN_OR_EQUALS = "<=";
    public static String LIKE = "like";
    public static String NOT_LIKE = "not like";

    private String key;
    private String operator;
    private Object value;

    private boolean ignoreCase = false;

    public ConditionItem(String key, String operator, Object value) {
        this.key = key;
        this.value = value;
        this.operator = operator;
    }

    public ConditionItem(String key, String operator, Object value, boolean ignoreCase) {
        this.key = key;
        this.value = value;
        this.operator = operator;
        this.ignoreCase = ignoreCase;
    }

    public String build() {
        if (ignoreCase) {
            return ignoreCaseBuild();
        }

        if (value instanceof List) {
            List listValue = (List) value;
            Object o = listValue.get(0);
            if (o instanceof String) {
                listValue.replaceAll(item -> "'" + item + "'");
                return key + " " + operator + " (" + JoinerUtils.PARAM_JOINER.join(listValue) + ")";
            } else {
                return key + " " + operator + " (" + JoinerUtils.PARAM_JOINER.join(listValue) + ")";
            }
        }
        if (value instanceof String) {
            return key + " " + operator + " '" + value + "'";
        }
        return key + " " + operator + " " + value;
    }

    private String ignoreCaseBuild() {
        if (value instanceof List) {
            List listValue = (List) value;
            Object o = listValue.get(0);
            if (o instanceof String) {
                listValue.replaceAll(item -> "'" + item.toString().toLowerCase() + "'");
                return lower(key) + " " + operator + " (" + JoinerUtils.PARAM_JOINER.join(listValue) + ")";
            } else {
                return lower(key) + " " + operator + " (" + JoinerUtils.PARAM_JOINER.join(listValue) + ")";
            }
        }
        if (value instanceof String) {
            return lower(key) + " " + operator + " '" + value.toString().toLowerCase() + "'";
        }

        return lower(key) + " " + operator + " " + value.toString().toLowerCase();
    }

    private String lower(String k) {
        return "lower(" + k + ")";
    }
}
