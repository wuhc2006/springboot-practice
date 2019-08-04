package com.whc.config.interceptor;

import com.whc.domain.entity.User;
import com.whc.service.UserService;
import com.whc.util.ContextUtil;
import com.whc.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.TimeUnit;

/**
 * @author Administrator
 * @date 2019/7/27 15:19
 */
@Component
public class LoginInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private UserService userService;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Object principal = ContextUtil.get();
        if (principal == null) {
            response.sendRedirect("/");
            return true;
        } else {
            return isValidUser(principal);
        }
    }

    /**
     * 是否为合法用户
     *
     * @return
     */
    private boolean isValidUser(Object principal) {
        String username = JwtUtil.getUsername(principal.toString());
        String s = redisTemplate.opsForValue().get(username);
        if (s != null) {
            return true;
        }
        User user = userService.findByName(username);
        if (user == null){
            return false;
        } else{
            redisTemplate.opsForValue().setIfAbsent(username, user.getUsername() + "_" +user.getId().toString(), 10, TimeUnit.MINUTES);
            return true;
        }
    }
}
