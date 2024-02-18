package cn.wenhe9.template.utils.lock.lock;

import java.util.concurrent.TimeUnit;

/**
 * @description: 自定义锁规范
 * <p>
 * 对于同一个代码块，根据key来控制，而不是使用传统AQS来控制
 * 这样多个线程过来如果key不同也都可以进入代码块
 * </p>
 * @author: DuJinliang
 * @create: 2023/3/16
 */
public interface ILock {
    /**
     * 每个线程默认有5秒争抢锁的时间，五秒内获取不到返回false,不会阻塞线程.
     * ①如果是单机版本的锁，抢到锁的线程不会自己释放锁
     * ②如果是分布式锁，抢到锁的线程会在30S后自动释放锁。
     */
    boolean acquire(String lockName);


    boolean acquire(String lockName, Integer expireTime);

    boolean acquire(String lockName, Long maxWaitTime, Integer expireTime);

    boolean acquire(String lockName, Long maxWaitTime, TimeUnit timeUnit, Integer expireTime, TimeUnit expireTimeUnit);

    void release(String lockName);
}
