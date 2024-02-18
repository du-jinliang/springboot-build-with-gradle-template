package cn.wenhe9.template.utils.lock.lock.impl;

import cn.wenhe9.template.utils.lock.lock.AbtrastILock;
import cn.wenhe9.template.utils.lock.lock.LockBeanNameConstants;
import cn.wenhe9.template.utils.spring.SpringUtil;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

/**
 * @description: 单机锁 使用 concurrentHashMap + threadLocal 实现
 * @author: DuJinliang
 * @create: 2023/3/16
 */

@Component(LockBeanNameConstants.CONCURRENT_HASHMAP_LOCK)
public class ConcurrentHashMapLock extends AbtrastILock {

    private final Map<String, String> map = new ConcurrentHashMap<>();

    private final ThreadLocal<String> uniqueIdTDL = new ThreadLocal<>();

    public ConcurrentHashMapLock(SpringUtil springUtil) {
        super(springUtil);
    }


    @Override
    protected void releaseLock(String lockName) {
        String uniqueId = uniqueIdTDL.get();
        if (uniqueId.equals(map.get(lockName))) {
            uniqueIdTDL.remove();
            map.remove(lockName);
        }

    }

    @Override
    protected boolean tryLock(String key, Integer expireTime, TimeUnit expireTimeUnit) {
        synchronized (ConcurrentHashMapLock.class) {
            if (!map.containsKey(key)) {
                String uniqueId = generateUniqueId();
                uniqueIdTDL.set(uniqueId);
                map.put(key, uniqueId);
                return Boolean.TRUE;
            }
            return Boolean.FALSE;
        }
    }

}
