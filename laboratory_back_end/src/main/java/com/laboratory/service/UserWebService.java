package com.laboratory.service;

import com.laboratory.entity.userWeb.UserWeb;
import com.laboratory.util.result.CommonResult;
import com.laboratory.util.result.PageResult;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.stereotype.Repository;

/**
 * @author echokilig
 * @date 2022/5/28 13:38
 */

@Repository
public interface UserWebService {

    /**
     * 根据id获取一条用户信息
     * @param id
     * @return
     */
    CommonResult<UserWeb> selectOneById(String id);
    /**
     * 分页查询用户列表
     *
     * @param currentPage
     * @param queryWrapper
     * @return CommonResult<PageResult < UserWeb>>
     */
    CommonResult<PageResult<UserWeb>> selectByPage(String currentPage, QueryWrapper<UserWeb> queryWrapper);

    /**
     * 按条件查询用户列表
     *
     * @param currentPage
     * @param type
     * @param text
     * @return CommonResult<PageResult < UserWeb>>
     */
    CommonResult<PageResult<UserWeb>> selectByCondition(String currentPage, String type, String text);

    /**
     * 根据id删除用户信息
     *
     * @param id
     * @return CommonResult
     */
    CommonResult deleteOneById(String id);

    /**
     * 根据id批量删除用户
     * @param id
     * @return
     */
    CommonResult deleteBatchById(String[] id);

    /**
     * 插入一个useWeb对象
     * @param userWeb
     * @return
     */
    CommonResult insertOneUser(UserWeb userWeb);

    /**
     *
     * @param userWeb
     * @return
     */
    CommonResult updateOneUser(UserWeb userWeb);
}
