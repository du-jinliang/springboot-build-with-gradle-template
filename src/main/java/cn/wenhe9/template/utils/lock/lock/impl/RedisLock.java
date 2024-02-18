package cn.wenhe9.template.utils.lock.lock.impl;

import cn.wenhe9.template.utils.lock.lock.AbtrastILock;
import cn.wenhe9.template.utils.lock.lock.LockBeanNameConstants;
import cn.wenhe9.template.utils.spring.SpringUtil;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Collections;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * @description: 分布式锁
 * @author: DuJinliang
 * @create: 2023/3/16
 */
@Component(LockBeanNameConstants.REDIS_LOCK)
@ConditionalOnBean(name = "redisTemplate")
public class RedisLock extends AbtrastILock {

    private final RedisTemplate<String, Object> redisTemplate;

    private final ThreadLocal<String> uniqueIdTDL = new ThreadLocal<>();

    private DefaultRedisScript<Long> lockScript;

    private DefaultRedisScript<Long> releaseScript;

    public RedisLock(SpringUtil springUtil, RedisTemplate<String, Object> redisTemplate) {
        super(springUtil);
        this.redisTemplate = redisTemplate;
    }

    @PostConstruct
    private void init() {
        lockScript = new DefaultRedisScript<>();
        lockScript.setResultType(Long.class);
        lockScript.setScriptText(
                "if redis.call('setnx',KEYS[1],ARGV[1]) == 1 then redis.call('expire',KEYS[1],ARGV[2]) return 1 else return 0 end"
        );

        releaseScript = new DefaultRedisScript<>();
        releaseScript.setResultType(Long.class);
        releaseScript.setScriptText(
                "if redis.call('get',KEYS[1]) == ARGV[1] then return redis.call('del',KEYS[1]) else return 0 end"
        );
    }

    @Override
    protected void releaseLock(String lockName) {
        redisTemplate.execute(
                releaseScript,
                Collections.singletonList(lockName),
                Collections.singletonList(uniqueIdTDL.get())
        );
    }

    @Override
    protected boolean tryLock(String key, Integer expireTime, TimeUnit expireTimeUnit) {
        String uniqueId = generateUniqueId();
        uniqueIdTDL.remove();
        uniqueIdTDL.set(uniqueId);
        return Objects.equals(redisTemplate.execute(
                lockScript,
                Collections.singletonList(key),
                Collections.singletonList(uniqueId),
                expireTime
        ), 1L);
    }


}
