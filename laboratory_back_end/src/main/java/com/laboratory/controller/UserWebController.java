package com.laboratory.controller;

import com.alibaba.fastjson.JSONObject;
import com.laboratory.entity.userWeb.UserWeb;
import com.laboratory.entity.userWeb.UserWebVo;
import com.laboratory.service.UserWebService;
import com.laboratory.util.result.CommonResult;
import com.laboratory.util.result.PageResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author echokilig
 * @date 2022/5/28 13:37
 */

@RestController
public class UserWebController {
    @Resource
    UserWebService userWebService;

    @GetMapping("/userWeb/select/oneUser/{id}")
    CommonResult<UserWeb> selectOneById(@PathVariable String id){
        return userWebService.selectOneById(id);
    }

    @GetMapping("/userweb/select/all")
    CommonResult<PageResult<UserWeb>> selectByPage(@RequestParam String currentPage) {
        return userWebService.selectByPage(currentPage, null);
    }

    @GetMapping("/userWeb/select/condition")
    CommonResult<PageResult<UserWeb>> selectByCondition(@RequestParam String currentPage,
                                                        @RequestParam String type,
                                                        @RequestParam String text) {
        return userWebService.selectByCondition(currentPage, type, text);
    }

    @GetMapping("/userWeb/delete/oneUser/{id}")
    CommonResult deleteOneById(@PathVariable String id) {
        return userWebService.deleteOneById(id);
    }

    @GetMapping("/userWeb/delete/batchUser")
    CommonResult deleteBatchById(@RequestParam String deleteId) {
        String[] deleteIds = deleteId.split(",");
        return userWebService.deleteBatchById(deleteIds);
    }

    @PostMapping(value = "/userWeb/insert/oneUser", produces = "application/json;charset=UTF-8")
    CommonResult insertOneUser(@RequestBody JSONObject jsonParam) {
        System.out.println("收到插入请求.");
        return userWebService.insertOneUser(new UserWeb(jsonParam.toJavaObject(UserWebVo.class)));
    }

    @PostMapping(value = "/userWeb/update/oneUser/{id}", produces = "application/json;charset=UTF-8")
    CommonResult updateOneUser(@RequestBody JSONObject jsonParam, @PathVariable String id){
        UserWeb userWeb = new UserWeb(jsonParam.toJavaObject((UserWebVo.class)));
        userWeb.setId(id);
        return userWebService.updateOneUser(userWeb);
    }
}
