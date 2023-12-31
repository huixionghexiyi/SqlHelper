package com.cloudwise.clickhouse.helper;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.lang3.StringUtils;

import com.google.common.collect.Lists;

import com.cloudwise.clickhouse.helper.annotations.ClickhouseTableField;

/**
 * @author timothy
 * @DateTime: 2023/6/20 18:16
 **/
public class SelectHelper {
    private static String convert2Underline(String camelCase) {
        String[] strings = StringUtils.splitByCharacterTypeCamelCase(camelCase);
        return JoinerUtils.UNDERLINE_JOINER.join(strings);
    }

    private static List<Field> getFields(Class<?> clazz) {
        Field[] fields = clazz.getDeclaredFields();
        return Stream.of(fields)
            .filter(f -> !Modifier.isStatic(f.getModifiers()))
            .filter(f -> !Modifier.isTransient(f.getModifiers()))
            .collect(Collectors.toList());
    }

    public static <T> String by(Class<T> clazz) {
        List<Field> fields = getFields(clazz);
        List<String> result = Lists.newArrayList();
        for (Field field : fields) {
            ClickhouseTableField annotation = field.getAnnotation(ClickhouseTableField.class);
            if (annotation != null) {
                if (StringUtils.isNotEmpty(annotation.value())) {
                    result.add(annotation.value());
                } else {
                    result.add(convert2Underline(field.getName()));
                }
            }
        }
        return JoinerUtils.PARAM_JOINER.join(result);
    }

    public static <T> String by(String tableAlias, Class<T> clazz) {
        List<Field> fields = getFields(clazz);
        List<String> result = Lists.newArrayList();
        for (Field field : fields) {
            ClickhouseTableField annotation = field.getAnnotation(ClickhouseTableField.class);
            if (annotation != null) {
                if (StringUtils.isNotEmpty(annotation.value())) {
                    result.add(tableAlias + "." + annotation.value());
                } else {
                    result.add(tableAlias + "." + convert2Underline(field.getName()));
                }
            }
        }
        return JoinerUtils.PARAM_JOINER.join(result);
    }

    public static String by(String tableAlias, List<String> select) {
        String[] ss = new String[select.size()];
        for (int i = 0; i < ss.length; i++) {
            ss[i] = tableAlias + "." + select.get(i);
        }
        return JoinerUtils.PARAM_JOINER.join(ss);
    }
}
