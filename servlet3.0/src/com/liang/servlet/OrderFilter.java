package com.liang.servlet;

import javax.servlet.*;
import java.io.IOException;

/**
 * @author: Liangxp
 * @date: 2019/7/11 23:07
 */
public class OrderFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        // 过滤请求
        System.out.println("order filter doFilter ...");
        // 放行
        filterChain.doFilter(servletRequest,servletResponse);
    }

    @Override
    public void destroy() {

    }
}
