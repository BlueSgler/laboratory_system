package com.laboratory.controller;

import com.alibaba.fastjson.JSONObject;
import com.laboratory.entity.user.UserVo;
import com.laboratory.service.UserService;
import com.laboratory.util.result.CommonResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author hss
 * @date 2022/6/7 13:29
 */

@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    UserService userService;

    @PostMapping(value = "/login", produces = "application/json;charset=UTF-8")
    CommonResult login(@RequestBody JSONObject jsonObject) {
        return userService.login(jsonObject.toJavaObject(UserVo.class));
    }

    @PostMapping(value = "/register", produces = "application/json;charset=UTF-8")
    CommonResult register(@RequestBody JSONObject jsonObject) {
        return userService.register(jsonObject.toJavaObject(UserVo.class));
    }
}
