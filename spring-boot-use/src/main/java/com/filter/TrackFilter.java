package com.filter;

import javax.servlet.*;
import java.io.IOException;

/**
 * 过滤器
 * 方法一：实现filter接口
 *
 */
public class TrackFilter implements Filter {


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("进入filter");
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }
}
