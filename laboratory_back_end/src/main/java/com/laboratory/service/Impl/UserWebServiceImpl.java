package com.laboratory.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.laboratory.entity.userWeb.UserWeb;
import com.laboratory.mapper.UserWebMapper;
import com.laboratory.service.UserWebService;
import com.laboratory.util.result.CommonResult;
import com.laboratory.util.result.PageResult;
import com.laboratory.util.result.ResultCode;
import com.laboratory.util.result.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author echokilig
 * @date 2022/5/28 15:08
 */

@Service
public class UserWebServiceImpl implements UserWebService {
    private static final Long PAGE_SIZE = 10L;

    @Autowired
    UserWebMapper userWebMapper;

    @Override
    public CommonResult<UserWeb> selectOneById(String id) {
        UserWeb userWeb = userWebMapper.selectById(id);
        if (userWeb == null) {
            CommonResult fail = ResultUtil.fail(ResultCode.CUSTOM_FAIL);
            fail.setMessage("没有id为" + id + "的用户");
            return fail;
        } else {
            return ResultUtil.success(userWeb);
        }
    }

    @Override
    public CommonResult<PageResult<UserWeb>> selectByPage(String currentPage, QueryWrapper<UserWeb> queryWrapper) {
        if (currentPage == null || "".equals(currentPage)) {
            currentPage = "1";
        }
        Page<UserWeb> page = new Page<>(Long.parseLong(currentPage), PAGE_SIZE);
        userWebMapper.selectPage(page, queryWrapper);
        PageResult<UserWeb> userWebList = new PageResult<>(page);
        if (userWebList.hasRecords()) {
            return ResultUtil.success(userWebList);
        } else {
            CommonResult fail = ResultUtil.fail(ResultCode.CUSTOM_FAIL);
            fail.setMessage("无记录");
            return fail;
        }
    }

    @Override
    public CommonResult<PageResult<UserWeb>> selectByCondition(String currentPage, String type, String text) {
        QueryWrapper<UserWeb> queryWrapper = new QueryWrapper<>();
        if (currentPage == null || "".equals(currentPage)) {
            currentPage = "1";
        }
        if ("id".equals(type)) {
            queryWrapper.like("id", text);
        } else if ("userName".equals(type)) {
            queryWrapper.like("username", text);
        } else if ("name".equals(type)) {
            queryWrapper.like("name", text);
        } else if ("class".equals(type)) {
            queryWrapper.like("class_name", text);
        } else if ("studentId".equals(type)) {
            queryWrapper.like("school_id", text);
        } else if ("role".equals(type)) {
            queryWrapper.like("role", text);
        }
        return selectByPage(currentPage, queryWrapper);
    }

    @Override
    public CommonResult deleteOneById(String id) {
        int delete = userWebMapper.deleteById(id);
        if (delete == 0) {
            CommonResult fail = ResultUtil.fail(ResultCode.CUSTOM_FAIL);
            fail.setMessage("没有id为" + id + "的商品");
            return fail;
        } else {
            return ResultUtil.success();
        }
    }

    @Override
    public CommonResult deleteBatchById(String[] id) {
        for (String s : id) {
            int delete = userWebMapper.deleteById(s);
            if (delete == 0) {
                CommonResult fail = ResultUtil.fail(ResultCode.CUSTOM_FAIL);
                return fail;
            }
        }
        return ResultUtil.success();
    }

    @Override
    public CommonResult insertOneUser(UserWeb userWeb) {
        int insert = userWebMapper.insert(userWeb);
        if (insert == 1) {
            return ResultUtil.success();
        } else {
            CommonResult fail = ResultUtil.fail(ResultCode.CUSTOM_FAIL);
            fail.setMessage("插入用户信息失败");
            return fail;
        }
    }

    @Override
    public CommonResult updateOneUser(UserWeb userWeb) {
        if (userWebMapper.updateById(userWeb) == 1) {
            return ResultUtil.success();
        } else {
            CommonResult fail = ResultUtil.fail(ResultCode.CUSTOM_FAIL);
            fail.setMessage("没有id为" + userWeb.getId() + "的用户");
            return fail;
        }
    }
}
