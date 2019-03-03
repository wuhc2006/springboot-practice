package com.whc.controller;

import com.whc.conf.ResponseData;
import com.whc.domain.User;
import com.whc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName UserAnnoCacheController
 * @Description TODO 注解形式完成redis缓存管理
 * @Author Administrator
 * @Date 2019/3/3 13:02
 * @Version 1.0
 */
@RestController
public class UserAnnoCacheController {

    @Autowired
    @Qualifier("userAnnoCacheService")
    private UserService userService;

    /**
     * 根据用户id获取详情
     *
     * @param id
     * @return
     */
    @GetMapping("/cache/getUserById")
    public User getUserById(Long id) {
        return userService.findUserById(id);
    }

    /**
     * 根据id删除用户
     *
     * @param id
     * @return
     */
    @RequestMapping("/cache/deleteUserById")
    public ResponseData deleteUserById(Long id){
        userService.deleteUserById(id);
        return new ResponseData(200, "删除成功！", null);
    }
}
