package com.liang.controller;

import com.liang.service.DeferredResultQueue;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.UUID;
import java.util.concurrent.Callable;

/**
 * springmvc 异步请求 ：1、返回Callable 2、返回DeferredResult
 * @author: Liangxp
 * @date: 2019/7/13 20:39
 */
@Controller
@RequestMapping("/userAsync")
public class UserAsyncController {

    @ResponseBody
    @RequestMapping("/insert")
    public DeferredResult<String> insert(){
        DeferredResult<String> deferredResult = new DeferredResult<String>(3000L, "create fail...");

        DeferredResultQueue.save(deferredResult);

        return deferredResult;
    }

    @ResponseBody
    @RequestMapping("/insertUser")
    public String insertUser(){
        // 创建用户
        String userId = UUID.randomUUID().toString();
        DeferredResult<String> deferredResult = DeferredResultQueue.get();
        deferredResult.setResult(userId);
        return "success===>" + userId;
    }

    /**
     * 1、控制器返回Callable
     * 2、Spring异步处理，将Callable 提交到 TaskExecutor 使用一个隔离的线程进行执行
     * 3、DispatcherServlet和所有的Filter退出web容器的线程，但是response 保持打开状态；
     * 4、Callable返回结果，SpringMVC将请求重新派发给容器，恢复之前的处理；
     * 5、根据Callable返回的结果。SpringMVC继续进行视图渲染流程等（从收请求-视图渲染）。
     *
     * preHandle.../springmvc-annotation/userAsync/processUpload
     * 主线程开始...Thread[http-apr-8180-exec-4,5,main]==>1563023250433
     * 主线程结束...Thread[http-apr-8180-exec-4,5,main]==>1563023250433
     * =========DispatcherServlet及所有的Filter退出线程============================
     *
     * ================等待Callable执行==========
     * 副线程开始...Thread[MvcAsync2,5,main]==>1563023250435
     * 副线程开始...Thread[MvcAsync2,5,main]==>1563023252435
     * ================Callable执行完成==========
     *
     * ================再次收到之前重发过来的请求========
     * preHandle.../springmvc-annotation/userAsync/processUpload
     * postHandle...（Callable的之前的返回值就是目标方法的返回值）
     * afterCompletion...public java.util.concurrent.Callable<java.lang.String>
     *
     * 异步的拦截器:
     * 	1）、原生API的AsyncListener
     * 	2）、SpringMVC：实现AsyncHandlerInterceptor；
     *
     *
     * @return
     */
    @RequestMapping("/processUpload")
    @ResponseBody
    public Callable<String> processUpload() {
        System.out.println("主线程开始..."+Thread.currentThread()+"==>"+System.currentTimeMillis());

        Callable<String> callable = new Callable<String>() {
            public String call() throws Exception {
                System.out.println("副线程开始..."+Thread.currentThread()+"==>"+System.currentTimeMillis());
                // 这里可以调用业务处理方法
                Thread.sleep(2000);
                System.out.println("副线程开始..."+Thread.currentThread()+"==>"+System.currentTimeMillis());
                return "someView";
            }
        };

        System.out.println("主线程结束..."+Thread.currentThread()+"==>"+System.currentTimeMillis());
        return callable;
    }
}
