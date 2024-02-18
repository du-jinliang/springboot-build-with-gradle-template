package cn.wenhe9.template.config.thread;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @description: 线程池配置类
 * @author: DuJinliang
 * @create: 2023/11/15
 */
@Configuration
public class ThreadPoolConfig {
    @Bean(name = "threadPoolExecutor")
    public ThreadPoolExecutor threadPoolExecutor(ThreadPoolConfigProperties threadPoolConfigProperties){
        return new ThreadPoolExecutor(
                threadPoolConfigProperties.CORE_SIZE,
                threadPoolConfigProperties.MAX_SIZE,
                threadPoolConfigProperties.KEEP_ALIVE_TIME,
                TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(100000),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy()
        );
    }
}
