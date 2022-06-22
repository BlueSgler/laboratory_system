package com.laboratory.util.redis;

import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author echokilig
 * @date 2022/6/7 11:55
 */

@Service
public interface RedisService {

    /**
     * 设置键
     *
     * @param key key
     * @param value value
     */
    void set(String key, Object value);
    /**
     * 存储值
     *
     * @param key 存储键key
     * @param value 存储值value
     * @param time 过期时间
     */
    void set(String key, Object value, Integer time);

    /**
     * 取值
     *
     * @param key 关键的key
     * @return 返回拿到的value
     */
    Object get(String key);

    /**
     * 删除属性
     *
     * @param key key
     * @return Boolean
     */
    Boolean del(String key);

    /**
     * 设置过期时间
     * @param key key
     * @param time time
     * @return Boolean
     */
    Boolean expire(String key, long time);

    /**
     * 获取过期时间
     * @param key key
     * @return Long
     */
    Long getExpire(String key);

    /**
     * 判断是否有该属性
     * @param key key
     * @return Boolean
     */
    Boolean hasKey(String key);
}
