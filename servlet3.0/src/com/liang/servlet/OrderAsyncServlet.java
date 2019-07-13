package com.liang.servlet;

import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * servlet 异步请求
 *
 * @author: Liangxp
 * @date: 2019/7/13 19:48
 */
@WebServlet(value = "/orderAsync", asyncSupported = true)
public class OrderAsyncServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1、支持异步处理 asyncSupported = true
        // 2、开启异步模式
        System.out.println("主线程开始。。。"+Thread.currentThread()+"==>"+System.currentTimeMillis());
        AsyncContext asyncContext = req.startAsync();

        // 3、业务逻辑进行异步处理
        asyncContext.start(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println("副线程开始。。。"+Thread.currentThread()+"==>"+System.currentTimeMillis());
                    insert();
                    asyncContext.complete();
                    //获取响应
                    ServletResponse response = asyncContext.getResponse();
                    response.getWriter().write("order async process end");
                    System.out.println("副线程结束。。。"+Thread.currentThread()+"==>"+System.currentTimeMillis());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        System.out.println("主线程结束。。。"+Thread.currentThread()+"==>"+System.currentTimeMillis());

    }

    /**
     * 业务方法，处理订单
     * @throws InterruptedException
     */
    public void insert() throws InterruptedException {
        System.out.println(Thread.currentThread() + "processing...");
        Thread.sleep(3000);
    }
}
