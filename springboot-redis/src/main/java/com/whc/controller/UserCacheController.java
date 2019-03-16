package com.whc.controller;

import com.whc.domain.ResponseData;
import com.whc.domain.User;
import com.whc.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * @ClassName UserController
 * @Description TODO 这是第一种缓存方式，手动设置key值
 * @Author Administrator
 * @Date 2019/2/25 22:29
 * @Version 1.0
 */
@RestController
public class UserCacheController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserCacheController.class);

    @Autowired
    @Qualifier("userCacheService")
    private UserService userService;

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 根据用户id获取详情
     * @param id
     * @return
     */
    @GetMapping("/getUserById")
    public User getUserById(Long id) {
        String key = "user_" + id;
        ValueOperations<String, Object> operations = redisTemplate.opsForValue();
        boolean hasKey = redisTemplate.hasKey(key);
        if (hasKey){
            User user = (User) operations.get(key);
            LOGGER.info("从缓存中获取了用户>>>>>>>>>>>>>>");
            return user;
        }
        // 从数据库获取
        User user = userService.findUserById(id);
        LOGGER.info("从数据库中获取了用户>>>>>>>>>>>>>>");
        // 插入缓存
        operations.set(key, user, 10, TimeUnit.MINUTES);
        LOGGER.info("插入用户缓存" + user.toString());
        return user;
    }

    /**
     * 根据id删除用户
     * @param id
     * @return
     */
    @RequestMapping("/deleteUserById")
    public ResponseData deleteUserById(Long id){
        userService.deleteUserById(id);
        // 删除缓存
        String key = "user_" + id;
        boolean hasKey = redisTemplate.hasKey(key);
        if (hasKey){
            redisTemplate.delete(key);
        }
        return new ResponseData(200, "删除成功！", null);
    }
}
