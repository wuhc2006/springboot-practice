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
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.TimeUnit;

/**
 * 这是第一种缓存方式，手动设置key值
 *
 * @author Administrator
 * @date 2019/2/25 22:29
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
     *
     * @param id
     * @return
     */
    @GetMapping("/getUserById/{id}")
    public User getUserById(@PathVariable Long id) {
        Assert.notNull(id, "id不允许为空!");
        String key = "user_" + id;
        ValueOperations<String, Object> operations = redisTemplate.opsForValue();
        boolean hasKey = redisTemplate.hasKey(key);
        if (hasKey) {
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

    @RequestMapping("/getCount")
    @ResponseBody
    public Integer getCount() {
        return 100;
    }

    /**
     * 根据id删除用户
     *
     * @param id
     * @return
     */
    @RequestMapping("/deleteUserById")
    public ResponseData deleteUserById(Long id) {
        userService.deleteUserById(id);
        // 删除缓存
        String key = "user_" + id;
        boolean hasKey = redisTemplate.hasKey(key);
        if (hasKey) {
            redisTemplate.delete(key);
        }
        return new ResponseData(200, "删除成功！");
    }
}
