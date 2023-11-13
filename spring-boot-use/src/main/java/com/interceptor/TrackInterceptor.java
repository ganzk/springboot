package com.interceptor;

import com.component.TestComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 拦截器
 * 有三个方法：
 * preHandle：
 * 在进入controller之前进行
 * 有返回值，能过拦截请求，返回true为通过
 *
 * postHandle：
 * 在执行完controller逻辑之后，return ModelAndView之前执行，
 * 无返回值,参数有ModelAndView，可以对ModelAndView进行操作
 *
 * afterCompletion：
 * 在执行controller类return之后执行，filter返回客户端之前执行
 * 无返回值
 *
 */
@Service
public class TrackInterceptor implements HandlerInterceptor {

    @Autowired
    TestComponent testComponent;

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        testComponent.getClass();
        System.out.println("preHandle");
        return true;
    }

    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {
        System.out.println("postHandle");
    }

    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
        System.out.println("afterCompletion");
    }

}
