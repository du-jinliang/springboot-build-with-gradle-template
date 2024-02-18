package cn.wenhe9.template.utils.lock.lock;

import cn.hutool.core.util.IdUtil;
import cn.wenhe9.template.utils.spring.SpringUtil;
import lombok.RequiredArgsConstructor;

import javax.annotation.PostConstruct;
import java.util.concurrent.TimeUnit;

/**
 * @description: 抽象自定义锁类
 * @author: DuJinliang
 * @create: 2023/3/16
 */
@RequiredArgsConstructor
public abstract class AbtrastILock implements ILock{

    private final String LOCK_KEY = "wenhe9:";

    private final String COLON = ":";

    private String APPLICAION_NAME = null;

    private final SpringUtil springUtil;

    @PostConstruct
    private void init() {
        APPLICAION_NAME = springUtil.getApplicationName() + COLON;
    }

    @Override
    public boolean acquire(String lockName) {
        return acquire(lockName, 5L, TimeUnit.SECONDS, 30, TimeUnit.SECONDS);
    }


    @Override
    public boolean acquire(String lockName, Integer expireTime) {
        return acquire(lockName, 5L, TimeUnit.SECONDS, expireTime, TimeUnit.SECONDS);
    }

    @Override
    public boolean acquire(String lockName, Long maxWaitTime, Integer expireTime) {
        return acquire(lockName, maxWaitTime, TimeUnit.SECONDS, expireTime, TimeUnit.SECONDS);
    }

    /** 获得锁
     * @param lockName 锁key
     * @param maxWaitTime 最大等待时间 如果是要求同一时间只能有一个人获得锁工作，后来者全部放弃，这里不填或不传
     * @param timeUnit 最大等待时间单位
     * @param expireTime 过期时间 防止死锁
     * @param expireTimeUnit 过期时间单位
     * @return 是否获得锁
     */
    @Override
    public boolean acquire(String lockName, Long maxWaitTime, TimeUnit timeUnit, Integer expireTime, TimeUnit expireTimeUnit) {

        if (maxWaitTime == null) {
            maxWaitTime = 0L;
        }

        int time = 0;
        boolean getLockResult = true;
        while (!tryLock(lockName, expireTime, expireTimeUnit)) {
            try {
                if (maxWaitTime == 0) {
                    getLockResult = false;
                    break;
                }
                timeUnit.sleep(1);
                time++;
                if (time == maxWaitTime) {
                    getLockResult = false;
                    return getLockResult;
                }
            } catch (InterruptedException e) {
                getLockResult = false;
                return getLockResult;
            }
        }
        return getLockResult;
    }


    @Override
    public void release(String lockName) {
        releaseLock(lockName);
    }

    protected abstract void releaseLock(String lockName);


    protected abstract boolean tryLock(String key, Integer expireTime, TimeUnit expireTimeUnit);

    protected String generateUniqueId() {
        return LOCK_KEY + APPLICAION_NAME + Thread.currentThread().getName() + COLON + IdUtil.randomUUID();
    }
}
