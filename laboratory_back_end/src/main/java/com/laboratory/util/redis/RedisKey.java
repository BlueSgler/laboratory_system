package com.laboratory.util.redis;

/**
 * @author echokilig
 * @date 2022/6/7 12:29
 */

public class RedisKey {
    public static final String ID = "id_";
    public static final String SESSION_ID = "session_id_";
    /**
     * 设置登录过期时间为30分钟
     */
    public static final Integer EXPIRE_TIME = 30 * 60;

    /**
     * 设置sessionId过期时间为一天
     */
    public static final Integer SESSION_ID_EXPIRE_TIME = 24 * 60 * 60;
}
