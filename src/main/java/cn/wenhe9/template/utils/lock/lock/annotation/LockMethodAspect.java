package cn.wenhe9.template.utils.lock.lock.annotation;

import cn.wenhe9.template.exception.BusinessException;
import cn.wenhe9.template.exception.ServerException;
import cn.wenhe9.template.result.ResultResponseEnum;
import cn.wenhe9.template.utils.lock.lock.ILock;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.Objects;

/**
 * @description: 给方法加锁切面类
 * @author: DuJinliang
 * @create: 2023/3/19
 */
@Slf4j
@Aspect
@Component
public class LockMethodAspect {
    @Resource
    private Map<String, ILock> iLockMap;

    @Around("@annotation(cn.wenhe9.utils.lock.annotation.Lockable)")
    public Object around(ProceedingJoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();

        Lockable lockable = method.getAnnotation(Lockable.class);

        ILock iLock = iLockMap.get(lockable.type());
        if (Objects.isNull(iLock)) {
            throw new ServerException(ResultResponseEnum.KNOWN_LOCK_BEAN_NAME);
        }

        String key = lockable.key();

        try {
            boolean isLock = iLock.acquire(key, lockable.waitTime(), lockable.timeUnit(), lockable.expire(), lockable.timeUnit());

            if (!isLock) {
                throw new BusinessException(ResultResponseEnum.CAN_NOT_GET_LOCK);
            }

            try {
                return joinPoint.proceed();
            }catch (Throwable throwable) {
                throw new ServerException(ResultResponseEnum.INTERNAL_SERVER_ERROR);
            }
        }finally {
            iLock.release(key);
        }
    }
}
