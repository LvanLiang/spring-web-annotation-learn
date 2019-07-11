package com.liang.servlet;

import com.liang.service.UserService;

import javax.servlet.*;
import javax.servlet.annotation.HandlesTypes;
import java.util.EnumSet;
import java.util.Set;

/**
 * @author: Liangxp
 * @Description:
 * @date: 2019/7/11 21:26
 */
//容器启动的时候会将@HandlesTypes指定的这个类型下面的子类（实现类，子接口等）传递过来；
//传入感兴趣的类型；
@HandlesTypes(value = {UserService.class})
public class MyServletContainerInitializer implements ServletContainerInitializer {
    /**
     * 应用启动的时候，会运行onStartup方法；
     * <p>
     * Set<Class<?>> set：感兴趣的类型的所有子类型；
     * ServletContext servletContext:代表当前Web应用的ServletContext；一个Web应用一个ServletContext；
     * <p>
     * 1）、使用ServletContext注册Web组件（Servlet、Filter、Listener）
     * 2）、使用编码的方式，在项目启动的时候给ServletContext里面添加组件；
     * 必须在项目启动的时候来添加；
     * 1）、ServletContainerInitializer得到的ServletContext；
     * 2）、ServletContextListener得到的ServletContext；
     */
    @Override
    public void onStartup(Set<Class<?>> set, ServletContext servletContext) throws ServletException {
        System.out.println("****感兴趣的类**** ");
        for (Class<?> aClass : set) {
            System.out.println(aClass);
        }


        // 注册servlet
        ServletRegistration.Dynamic orderServlet = servletContext.addServlet("orderServlet", new OrderServlet());
        // 配置servlet的映射信息
        orderServlet.addMapping("/order");


        // 注册Filter
        FilterRegistration.Dynamic orderFilter = servletContext.addFilter("orderFilter", OrderFilter.class);
        // 配置Filter的映射信息
        orderFilter.addMappingForUrlPatterns(EnumSet.of(DispatcherType.REQUEST),true,"/*");


        //注册listener
        servletContext.addListener(OrderListener.class);


    }
}
