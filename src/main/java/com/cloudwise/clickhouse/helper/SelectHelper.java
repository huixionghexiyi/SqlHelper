package com.cloudwise.clickhouse.helper;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;

import com.baomidou.mybatisplus.annotation.TableField;
import com.google.common.collect.Lists;

/**
 * @author timothy
 * @DateTime: 2023/6/20 18:16
 **/
public class SelectHelper {

    public static String buildSelectParamByClass(Class clazz) {
        Field[] fields = clazz.getDeclaredFields();
        List<String> result = Lists.newArrayList();
        for (Field field : fields) {
            TableField tableField = field.getAnnotation(TableField.class);
            if (tableField != null) {
                result.add(tableField.value());
            } else {
                result.add(convert2Underline(field.getName()));
            }
        }

        List<String> paramList = Arrays.stream(fields).map(Field::getName)
            .collect(Collectors.toList());
        return ClickhouseHelper.PARAM_JOINER.join(paramList);
    }

    private static String convert2Underline(String camelCase) {
        String[] strings = StringUtils.splitByCharacterTypeCamelCase(camelCase);
        return ClickhouseHelper.UNDERLINE_JOINER.join(strings);
    }
}
