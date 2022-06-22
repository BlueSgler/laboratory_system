package com.laboratory.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.laboratory.entity.user.User;
import com.laboratory.entity.user.UserVo;
import com.laboratory.mapper.UserMapper;
import com.laboratory.service.UserService;
import com.laboratory.util.redis.RedisKey;
import com.laboratory.util.redis.RedisService;
import com.laboratory.util.result.CommonResult;
import com.laboratory.util.result.ResultCode;
import com.laboratory.util.result.ResultUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.UUID;

/**
 * @author hss
 * @date 2022/6/7 13:28
 */

@Service
public class UserServiceImpl implements UserService {
    private static String USERNAME = "username";

    @Resource
    UserMapper userMapper;
    @Resource
    RedisService redisService;

    @Override
    public CommonResult login(UserVo userVo) {
        User user = userMapper.selectOne(new QueryWrapper<User>().eq(USERNAME, userVo.getUsername()));
        if (user != null) {
            boolean equals = user.getPassword().equals(userVo.getPassword());
            if (equals) {
                String key = RedisKey.ID + user.getId();
                String getSessionId = (String) redisService.get(key);
                String sessionId;
//                sessionId为空, 重新生成sessionId
                if (StringUtils.isBlank(getSessionId)) {
                    sessionId = user.getId() + "_" + UUID.randomUUID();
//                    sessionId过期时间为一天
                    redisService.set(key, sessionId, RedisKey.SESSION_ID_EXPIRE_TIME);
//                    登录后将信息存储到redis中, 设置过期时间为一天
                    redisService.set(RedisKey.SESSION_ID + sessionId, userVo.getUsername(), RedisKey.SESSION_ID_EXPIRE_TIME);
                    return ResultUtil.success(sessionId);
                } else {
//                    重置sessionId过期时间
                    redisService.expire(key, RedisKey.SESSION_ID_EXPIRE_TIME);
//                    sessionId不为空, 则重新设置
                    redisService.set(RedisKey.SESSION_ID + getSessionId, userVo.getUsername(), RedisKey.SESSION_ID_EXPIRE_TIME);
                    return ResultUtil.success(getSessionId);
                }
            } else {
                return ResultUtil.fail(ResultCode.USER_CREDENTIALS_ERROR);
            }
        } else {
            return ResultUtil.fail(ResultCode.USER_ACCOUNT_NOT_EXIST);
        }
    }

    @Override
    public CommonResult register(UserVo userVo) {
//        判断用户是否存在
        if (userMapper.selectOne(new QueryWrapper<User>().eq(USERNAME, userVo.getUsername())) != null) {
            return ResultUtil.fail(ResultCode.USER_ACCOUNT_ALREADY_EXIST);
        } else {
            int insert = userMapper.insert(new User(userVo));
            return insert == 0 ? ResultUtil.fail("数据库操作异常") : ResultUtil.success();
        }
    }
}
