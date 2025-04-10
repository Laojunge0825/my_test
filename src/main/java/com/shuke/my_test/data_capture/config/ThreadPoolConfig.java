package com.shuke.my_test.data_capture.config;


import cn.hutool.core.thread.ThreadFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author 舒克、舒克
 * @date 2025/4/10 09:55
 * @description 线程池配置类  动态线程配置
 * 1. 核心线程数：根据系统资源情况和任务类型来设置，一般设置为CPU核心数+1。
 * 2. 最大线程数：根据系统资源情况和任务类型来设置，一般设置为CPU核心数*2。
 * 3. 队列容量：根据系统资源情况和任务类型来设置，一般设置为任务数量的10倍。
 * 4. 线程存活时间：根据系统资源情况和任务类型来设置，一般设置为60秒。
 * 5. 拒绝策略：根据系统资源情况和任务类型来设置，一般设置为CallerRunsPolicy。
 * 6. 线程工厂：根据系统资源情况和任务类型来设置，一般设置为ThreadFactoryBuilder。
 * 7. 线程池名称：根据系统资源情况和任务类型来设置，一般设置为线程池名称。
 * 8. 线程池类型：根据系统资源情况和任务类型来设置，一般设置为ThreadPoolExecutor。
 */
@Configuration
public class ThreadPoolConfig {

    @Bean("dataParseThreadPool")
    public ExecutorService dataParseThreadPool() {
        return new ThreadPoolExecutor(
                2, // 核心线程数
                8, // 最大线程数
                60L,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(100),
                // 增强的线程工厂
                new ThreadFactory() {
                    private final AtomicInteger counter = new AtomicInteger(1);

                    @Override
                    public Thread newThread(Runnable r) {
                        Thread t = new Thread(r);
                        t.setName("data-parser-" + counter.getAndIncrement());
                        return t;
                    }
                },
                new ThreadPoolExecutor.CallerRunsPolicy());
    }
}