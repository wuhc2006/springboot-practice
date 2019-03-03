package com.whc;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.whc.service.RedisService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootRedisApplicationTests {

    @Autowired
    private RedisService redisService;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Test
    public void contextLoads() {
    }

    /**
     * 插入字符串
     */
    @Test
    public void setString() {
        redisTemplate.opsForValue().set("springboot-redis", "springboot redis string set!");
    }

    /**
     * 获取字符串
     *
     * @return
     */
    @Test
    public void getString() {
        System.err.println(redisTemplate.opsForValue().get("springboot-redis"));
    }

    @Test
    public void setObj() {
        Person person = new Person("admin", 23, "ww@163.com", new BigDecimal(1000.00));
        redisTemplate.opsForValue().set("redis_obj_key", JSON.toJSONString(person));
    }

    @Test
    public void getObj() {
        System.err.println(redisTemplate.opsForValue().get("redis_obj_key").toString());
    }

}

