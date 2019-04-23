package com.whc.conf.jedis;

import com.whc.util.SerializeUtil;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.io.IOException;
import java.util.Properties;

/**
 * redis缓存相关的配置和以及redis操作
 */
public class RedisCache {

    private static Properties pps = new Properties();
    private static JedisPool jedisPool;
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
     * @param timeout 过期时间，单位s
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
            jedis = initJedis();
        } else {
            jedis = jedisPool.getResource();
        }

        return jedis;
    }

    /**
     * 初始化jedis
     * @return
     */
    private static Jedis initJedis() {
        Jedis jedis;
        try {
            pps.load(RedisCache.class.getClassLoader().getResourceAsStream("redis.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        JedisPool jp = new JedisPool(poolConfig,
                pps.getProperty("host"),
                Integer.valueOf(pps.getProperty("port")),
                Integer.valueOf(pps.getProperty("timeout")),
                pps.getProperty(pps.getProperty("password")),
                Integer.valueOf(pps.getProperty("database")));

        jedis = jp.getResource();
        jedisPool = jp;
        return jedis;
    }

    static {

    }

    private static void close(Jedis jedis) {
        try{
            jedisPool.returnResource(jedis);
        }catch (Exception e){
            if(jedis.isConnected()){
                jedis.quit();
                jedis.disconnect();
            }
        }
    }

}
