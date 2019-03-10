package com.whc.conf.jedis;

import com.whc.util.SerializeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.thymeleaf.expression.Maps;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static com.whc.conf.jedis.RedisCache.getJedis;

/**
 * @ClassName RedisUtil
 * @Description TODO
 * <p>
 * 此工具与RedisCache工具的putObject和getObject类似
 * 目前存在jedisPool无法注入的问题 2019.3.10
 * </p>
 * @Author Administrator
 * @Date 2019/3/10 11:27
 * @Version 1.0
 */
@Component
public class RedisCache2 {

    @Autowired
    private JedisPool jedisPool;

    @Autowired
    private StringRedisTemplate redisTemplate;

    /**
     * 给某个key设值
     *
     * @param key
     * @param value
     */
    public void set(Object key, Object value) {
        Jedis client = jedisPool.getResource();
        try {
            byte[] keyBytes = SerializeUtil.serialize(key);
            byte[] valueBytes = SerializeUtil.serialize(value);
            client.set(keyBytes, valueBytes);
        } finally {
            client.close();
        }

    }

    /**
     * 根据key获取value
     *
     * @param key
     * @return
     */
    public Object get(Object key) {
        Jedis client = getJedis();
        try {
            byte[] keyBytes = SerializeUtil.serialize(key);
            byte[] valueBytes = client.get(keyBytes);
            return SerializeUtil.deserialize(valueBytes);
        } finally {
            client.close();
        }
    }

    /**
     * 根据键值获取value
     *
     * @param key
     * @param field
     * @return
     */
    public Object hashGet(Object key, Object field) {
        Jedis client = getJedis();
        try {
            byte[] keyBytes = SerializeUtil.serialize(key);
            byte[] fieldBytes = SerializeUtil.serialize(field);
            byte[] valueBytes = client.hget(keyBytes, fieldBytes);
            return SerializeUtil.deserialize(valueBytes);
        } finally {
            client.close();
        }

    }

    /**
     * @param key
     * @param field
     * @param value
     */
    public void hashSet(Object key, Object field, Object value) {
        Jedis client = getJedis();
        try {
            byte[] keyBytes = SerializeUtil.serialize(key);
            byte[] fieldBytes = SerializeUtil.serialize(field);
            byte[] valueBytes = SerializeUtil.serialize(value);
            client.hset(keyBytes, fieldBytes, valueBytes);
        } finally {
            client.close();
        }

    }

    /**
     * @param key
     * @return
     */
    public Map<Object, Object> hashAllGet(Object key) {
        Jedis client = getJedis();
        try {
            byte[] keyBytes = SerializeUtil.serialize(key);
            Map<byte[], byte[]> map = client.hgetAll(keyBytes);
            Map<Object, Object> valueMap = new HashMap<>();
            Set<Map.Entry<byte[], byte[]>> valueSet = map.entrySet();
            for (Map.Entry<byte[], byte[]> entry : valueSet) {
                valueMap.put(SerializeUtil.deserialize(entry.getKey()), SerializeUtil.deserialize(entry.getValue()));
            }
            return valueMap;
        } finally {
            client.close();
        }

    }

    /**
     * 判断key是否存在
     *
     * @param key
     * @return
     */
    public boolean existKey(Object key) {
        Jedis client = getJedis();
        try {
            byte[] keyBytes = SerializeUtil.serialize(key);
            return client.exists(keyBytes);
        } finally {
            client.close();
        }
    }
}
