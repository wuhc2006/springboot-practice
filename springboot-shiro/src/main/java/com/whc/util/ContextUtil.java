package com.whc.util;

import org.apache.shiro.SecurityUtils;

/**
 * @author Administrator
 * @date 2019/7/26 22:54
 */
public class ContextUtil {

    /**
     * 获取当前登录的用户
     *
     * @return
     */
    public static Object get(){
        return SecurityUtils.getSubject().getPrincipal();
    }
}
