package com.whc.conf;

import com.whc.util.SerializeUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.io.IOException;
import java.util.Properties;

/**
 * 缓存
 */
public class RedisCache {

    private static String hostname = "localhost";
    private static int port = 6379;
    private static String password = "";

    private static Properties pps = new Properties();

    private static JedisPool jedisPool;

    public static JedisPool getJedisPool() {
        return RedisCache.jedisPool;
    }

    public static void setJedisPool(JedisPool jedisPool) {
        RedisCache.jedisPool = jedisPool;
    }

    /**
     * 清空所有缓存
     */
    public static void clear() {
        getJedis().flushDB();
    }

    /**
     * 设值
     *
     * @param key
     * @param value
     */
    public static void putObject(Object key, Object value, int timeout) {
        Jedis jedis = getJedis();
        jedis.set(SerializeUtil.serialize(key), SerializeUtil.serialize(value));
        jedis.expire(SerializeUtil.serialize(key), timeout);
    }

    /**
     * 取值
     *
     * @param key
     * @return
     */
    public static Object getObject(Object key) {

        byte[] bytes = SerializeUtil.serialize(key);
        byte[] value = getJedis().get(bytes);
        if (value == null) {
            return null;
        }
        return SerializeUtil.deserialize(value);
    }

    /**
     * 移除key
     *
     * @param key
     * @return
     */
    public static Object removeObject(Object key) {
        Jedis jedis = getJedis();

        byte[] bytes = jedis.get(SerializeUtil.serialize(key));

        jedis.del(SerializeUtil.serialize(key));

        return SerializeUtil.deserialize(bytes);
    }

    public static int getSize() {
        Long size = getJedis().dbSize();
        return size.intValue();
    }

    /**
     * 获取一个jedis连接
     *
     * @return
     */
    public static Jedis getJedis() {
        Jedis jedis = null;
        if (jedisPool == null) {
            try {
                pps.load(RedisCache.class.getClassLoader().getResourceAsStream("application.properties"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            JedisPoolConfig poolConfig = new JedisPoolConfig();
            JedisPool jp = new JedisPool(poolConfig,
                    pps.getProperty("spring.redis.host"),
                    Integer.valueOf(pps.getProperty("spring.redis.port")),
                    5000,
                    pps.getProperty(pps.getProperty("spring.redis.password")),
                    Integer.valueOf(pps.getProperty("spring.redis.database")));

            jedis = jp.getResource();
            jedisPool = jp;
        } else {
            jedis = jedisPool.getResource();
        }

        return jedis;
    }

    static {

    }

}
