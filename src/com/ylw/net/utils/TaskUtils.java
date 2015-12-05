package com.ylw.net.utils;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class TaskUtils {
    public static Executor getExcutor() {
        if (executorService == null) {
            executorService = new ThreadPoolExecutor(kDefaultThreadPoolSize, maxThreadPoolSize, kKeepAliveTime, kTimeUnit, queue,
                    handler);
        }
        return executorService;
    }

    public static Executor getSingleExcutor() {
        if (singleExecutorService == null) {
            singleExecutorService = Executors.newSingleThreadExecutor();
        }
        return singleExecutorService;
    }

    private static int kDefaultThreadPoolSize = 4;
    private static int maxThreadPoolSize = 40;
    private static int kKeepAliveTime = 30;
    private static TimeUnit kTimeUnit = TimeUnit.SECONDS;

    private final static LinkedBlockingQueue<Runnable> queue = new LinkedBlockingQueue<Runnable>();

    static RejectedExecutionHandler handler = new RejectedExecutionHandler() {
        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
            queue.add(r);
        }
    };

    private static ExecutorService executorService;
    private static ExecutorService singleExecutorService;

}
