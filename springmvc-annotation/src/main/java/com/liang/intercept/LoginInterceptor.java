package com.liang.intercept;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * springmvc 自定义登录拦截器
 * @author: Liangxp
 * @date: 2019/7/13 18:12
 */
public class LoginInterceptor implements HandlerInterceptor {
    /**
     * 目标方法运行之前执行
     */
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("preHandle..."+request.getRequestURI());
        return true;
    }

    /**
     * 目标方法执行正确以后执行
     */
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("postHandle..."+modelAndView.getViewName());
    }

    /**
     * 页面响应以后执行
     */
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("afterCompletion..."+handler);
    }
}
