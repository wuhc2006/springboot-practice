package com.whc.config.interceptor;

import com.whc.util.ContextUtil;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Administrator
 * @date 2019/7/27 15:19
 */
public class LoginInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (ContextUtil.get() == null){
            response.sendRedirect("/login");
            return false;
        }
        return true;
    }
}
