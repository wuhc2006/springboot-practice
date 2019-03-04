package com.whc.service.impl;

import com.whc.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @ClassName RedisServiceImpl redis服务实现类
 * @Description TODO
 * @Author Administrator
 * @Date 2019/3/3 10:48
 * @Version 1.0
 */
@Deprecated
@Service
public class RedisServiceImpl implements RedisService {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;


    @Override
    public boolean set(String key, Object value) {
        redisTemplate.execute((RedisConnection redisConnection) -> {
            RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
            redisConnection.set(serializer.serialize(key), serializer.serialize(value.toString()));
            return true;
        });
        return false;
    }

    @Override
    public String get(String key) {
        redisTemplate.execute((RedisConnection redisConnection) -> {
            RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
            byte[] value = redisConnection.get(serializer.serialize(key));
            return serializer.deserialize(value);
        });
        return null;
    }

    @Override
    public boolean expire(String key, long timeout) {
        return redisTemplate.expire(key, timeout, TimeUnit.SECONDS);
    }

    @Override
    public boolean remove(String key) {
        redisTemplate.execute((RedisConnection redisConnection) -> {
            RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
            return redisConnection.del(serializer.serialize(key));
        });
        return false;
    }
}
