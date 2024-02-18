package cn.wenhe9.template.utils;


import cn.wenhe9.template.exception.ValidationException;

import java.util.Collection;
import java.util.Map;
import java.util.Objects;

/**
 * @description: 合法性校验工具类
 * @author: DuJinliang
 * @create: 2023/1/26
 */
public class ValidationUtil {


    public static void isTrue(Boolean expect) {
        isTrue(expect, "校验异常");
    }

    public static void isTrue(Boolean expect, String message) {
        if (expect) {
            throw ValidationException.of(message);
        }
    }

    public static void isFalse(Boolean expect, String message) {
        isTrue(!expect, message);
    }


    public static void isNull(Object object) {
        isNull(object, "入参为空");
    }

    public static void isNull(Object object, String message) {
        if (Objects.isNull(object)) {
            throw ValidationException.of(message);
        }
    }

    public static void isNull(Collection<?> collection) {
        isNull(collection, "集合为空");
    }

    public static void isNull(Collection<?> collection, String message) {
        if ((collection == null || collection.isEmpty())) {
            throw ValidationException.of(message);
        }
    }

    public static void isNull(Map<?, ?> map) {
        isNull(map, "map为空");
    }

    public static void isNull(Map<?, ?> map, String message) {
        if ((map == null || map.isEmpty())) {
            throw ValidationException.of(message);
        }
    }

    public static void notNull(Object object, String message) {
        if (Objects.nonNull(object)) {
            throw ValidationException.of(message);
        }
    }

    public static void notNull(Collection<?> collection, String message) {
        if (!(collection == null || collection.isEmpty())) {
            throw ValidationException.of(message);
        }
    }

    public static void notNull(Map<?, ?> map, String message) {
        if (!(map == null || map.isEmpty())) {
            throw ValidationException.of(message);
        }
    }
    public static void isAssert(String message) {
        throw ValidationException.of(message);
    }

}
