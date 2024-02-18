package cn.wenhe9.template.utils.lock.lock.annotation;


import cn.wenhe9.template.utils.lock.lock.LockBeanNameConstants;

import java.lang.annotation.*;
import java.util.concurrent.TimeUnit;

/**
 * @description: 加锁注解
 * @author: DuJinliang
 * @create: 2023/3/19
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface Lockable {
    /**
     * 加锁类型
     */
    String type() default LockBeanNameConstants.CONCURRENT_HASHMAP_LOCK;

    /**
     * 业务键
     */
    String key();
    /**
     * 锁的过期秒数,默认是5秒
     */
    int expire() default 5;

    /**
     * 尝试加锁，最多等待时间
     */
    long waitTime() default Long.MIN_VALUE;
    /**
     * 锁的超时时间单位
     */
    TimeUnit timeUnit() default TimeUnit.SECONDS;
}
