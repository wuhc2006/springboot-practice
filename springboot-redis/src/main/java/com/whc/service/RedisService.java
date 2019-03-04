package com.whc.service;

/**
 * @TODO redis操作服务类
 * @Author whc
 * @Date 2019/3/3
 */
@Deprecated
public interface RedisService {
    /**
     * 设置key,value
     * @param key
     * @param value
     * @return
     */
    boolean set(String key, Object value);

    /**
     * 获取key键对应的值
     * @param key
     * @return
     */
    Object get(String key);

    /**
     * 设置过期时间
     * @param key
     * @param timeout
     * @return
     */
    boolean expire(String key, long timeout);

    /**
     * 移除key
     * @param key
     * @return
     */
    boolean remove(String key);
}
