package com.laboratory.service;

import com.laboratory.entity.user.UserVo;
import com.laboratory.util.result.CommonResult;
import org.springframework.stereotype.Service;

/**
 * @author hss
 * @date 2022/6/7 13:29
 */

@Service
public interface UserService {

    /**
     * 登录
     * @param userVo 登录账号密码
     * @return 返回统一封装类
     */
    CommonResult login(UserVo userVo);

    /**
     * 注册
     * @param userVo 注册信息
     * @return 返回统一封装类
     */
    CommonResult register(UserVo userVo);
}
