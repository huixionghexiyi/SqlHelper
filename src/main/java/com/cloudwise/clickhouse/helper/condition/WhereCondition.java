package com.cloudwise.clickhouse.helper.condition;

import java.util.List;

import com.google.common.collect.Lists;

import com.cloudwise.clickhouse.helper.JoinerUtils;

/**
 * where 条件构建 一个 whereCondition代表一个 and 条件 多个 and 条件通过 and() 方法 嵌套 多个 or 条件通过 or() 方法嵌套 example: WhereCondition
 * condition = new WhereCondition(); condition.like("host_name", "%L%"); condition.eq("host_ip", "10.0.12.87")
 * condition.or(new WhereCondition().eq("role", "admin").eq("manager", "timothy")) condition.and(new
 * WhereCondition().notIn("role", new String[]{"admin", "reader"}).eq("cpuCore", 4)) 等价于: where (host_name like '%L%'
 * and host_ip = '10.0.12.87') or (role = 'admin' and manager = 'timothy') and (role not in ('admin', 'reader') and
 * cpuCore = 4)
 *
 * @author timothy
 * @DateTime: 2023/6/21 14:16
 **/
public class WhereCondition {

    private List<ConditionItem> data;

    private StringBuilder result;

    public WhereCondition() {
        data = Lists.newArrayList();
        result = new StringBuilder();
    }

    private void add(ConditionItem item) {
        data.add(item);
    }

    public WhereCondition in(String key, List<Object> values, boolean... ignoreCase) {
        if (ignoreCase.length > 0 && ignoreCase[0]) {
            this.add(new ConditionItem(key, ConditionItem.IN, values, true));
        } else {
            this.add(new ConditionItem(key, ConditionItem.IN, values));
        }
        return this;
    }

    public WhereCondition notIn(String key, List<Object> values, boolean... ignoreCase) {
        if (ignoreCase.length > 0 && ignoreCase[0]) {
            this.add(new ConditionItem(key, ConditionItem.NOT_IN, values, true));
        } else {
            this.add(new ConditionItem(key, ConditionItem.NOT_IN, values));
        }
        return this;
    }

    public WhereCondition eq(String key, Object value, boolean... ignoreCase) {
        if (ignoreCase.length > 0 && ignoreCase[0]) {
            this.add(new ConditionItem(key, ConditionItem.EQUALS, value, true));
        } else {
            this.add(new ConditionItem(key, ConditionItem.EQUALS, value));
        }
        return this;
    }

    public WhereCondition or(WhereCondition orCondition) {
        build();
        this.result.append(" OR ").append(orCondition.build());
        return this;
    }

    public WhereCondition and(WhereCondition andCondition) {
        build();
        this.result.append(" AND ").append(andCondition.build());
        return this;
    }

    private String build() {
        String result = JoinerUtils.WHERE_CONDITION_JOINER.join(data.stream().map(ConditionItem::build).toArray());
        this.result.append('(').append(result).append(')');
        data.clear();
        return this.result.toString();
    }

    public String getCondition() {
        if (data.size() > 0) {
            build();
        }
        return result.toString();
    }
}
