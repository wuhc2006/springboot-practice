package com.whc.conf.jedis;

import com.whc.util.SerializeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.io.IOException;
import java.util.Properties;

/**
 * redis缓存相关的配置和以及redis操作
 */
public class RedisCache {

    private static final Logger log = LoggerFactory.getLogger(RedisCache.class);

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
        Jedis jedis = null;
        try{
            jedis = getJedis();
            jedis.set(SerializeUtil.serialize(key), SerializeUtil.serialize(value));
            if (0 < timeout && timeout < Integer.MAX_VALUE){
                jedis.expire(SerializeUtil.serialize(key), timeout);
            }
        }catch (Exception e){
            log.error(e.getMessage(), e);
        }finally {
            close(jedis);
        }
    }

    /**
     * 取值
     *
     * @param key
     * @return
     */
    public static Object getObject(Object key) {

        byte[] bytes = SerializeUtil.serialize(key);
        Jedis jedis = null;
        try{
            jedis = getJedis();
            byte[] value = jedis.get(bytes);
            if (value == null) {
                return null;
            }
            return SerializeUtil.deserialize(value);
        }finally {
            close(jedis);
        }
    }

    /**
     * 移除key
     *
     * @param key
     * @return
     */
    public static Object removeObject(Object key) {
        Jedis jedis = null;
        try{
            jedis = getJedis();
            byte[] bytes = jedis.get(SerializeUtil.serialize(key));
            jedis.del(SerializeUtil.serialize(key));
            return SerializeUtil.deserialize(bytes);
        } finally {
            close(jedis);
        }

    }

    public static int getSize() {
        Jedis jedis = null;
        try{
            jedis = getJedis();
            Long size = getJedis().dbSize();
            return size.intValue();
        }finally {
            close(jedis);
        }
    }

    /**
     * 获取一个jedis连接
     *
     * @return
     */
    public synchronized static Jedis getJedis() {
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
                Integer.parseInt(pps.getProperty("port")),
                Integer.parseInt(pps.getProperty("timeout")),
                pps.getProperty(pps.getProperty("password")),
                Integer.parseInt(pps.getProperty("database")));

        jedis = jp.getResource();
        jedisPool = jp;
        return jedis;
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
