package cn.wenhe9.template.config.thread;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @description: 线程池配置属性 从配置文件中获取
 * @author: DuJinliang
 * @create: 2022/12/17
 */
@Component
public class ThreadPoolConfigProperties implements InitializingBean {
    @Value("${wenhe9.thread.core-size}")
    private Integer coreSize;

    @Value("${wenhe9.thread.max-size}")
    private Integer maxSize;

    @Value("${wenhe9.thread.keep-alive-time}")
    private Integer keepAliveTime;

    public  Integer CORE_SIZE;

    public  Integer MAX_SIZE;

    public  Integer KEEP_ALIVE_TIME;

    @Override
    public void afterPropertiesSet() throws Exception {
        CORE_SIZE = this.coreSize;
        MAX_SIZE = this.maxSize;
        KEEP_ALIVE_TIME = this.keepAliveTime;
    }
}
