package com.laboratory.interceptor;

import com.alibaba.fastjson.JSON;
import com.laboratory.util.redis.RedisKey;
import com.laboratory.util.redis.RedisService;
import com.laboratory.util.result.ResultCode;
import com.laboratory.util.result.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author echokilig
 * @date 2022/6/7 15:00
 */

@Component
@Slf4j
public class LoginInterceptor implements HandlerInterceptor {
    @Resource
    RedisService redisService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        静态方法直接通过
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        String sessionIdWithHeader = request.getHeader("Authorization");
        if (StringUtils.isBlank(sessionIdWithHeader)) {
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().write(JSON.toJSONString(ResultUtil.fail(ResultCode.TOKEN_NULL)));
            return false;
        }
        String sessionId = sessionIdWithHeader.split(" ")[1];
        log.info("=================request start===========================");
        log.info("request uri:{}", request.getRequestURI());
        log.info("request method:{}", request.getMethod());
        log.info("session_id:{}", sessionId);
        log.info("=================request end===========================");
        String user = (String) redisService.get(RedisKey.SESSION_ID + sessionId);
        if (StringUtils.isBlank(user)) {
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().write(JSON.toJSONString(ResultUtil.fail(ResultCode.TOKEN_EXPIRED)));
            return false;
        }
        return true;
    }
}
