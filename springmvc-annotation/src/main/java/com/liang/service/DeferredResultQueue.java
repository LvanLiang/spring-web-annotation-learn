package com.liang.service;

import org.springframework.web.context.request.async.DeferredResult;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * 模拟消息队列
 * @author: Liangxp
 * @date: 2019/7/13 20:52
 */
public class DeferredResultQueue {
    private static Queue<DeferredResult<String>> queue = new ConcurrentLinkedQueue<DeferredResult<String>>();

    public static void save(DeferredResult<String> deferredResult){
        queue.add(deferredResult);
    }

    public static DeferredResult<String> get( ){
        return queue.poll();
    }
}
