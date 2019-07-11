package com.liang.servlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * @author: Liangxp
 * @Description: 监听项目的启动和停止
 * @date: 2019/7/11 23:10
 */
public class OrderListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        ServletContext servletContext = servletContextEvent.getServletContext();
        System.out.println("OrderListener contenxtIniaialized ...");
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        System.out.println("OrderListener contextDestoryed ...");
    }
}
