package com.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * 过滤器
 * 第二种：
 *
 */
@WebFilter(filterName = "trackScanFilter",urlPatterns = {"/*"})
public class TrackScanFilter implements Filter {


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("进入注解的filter");
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
