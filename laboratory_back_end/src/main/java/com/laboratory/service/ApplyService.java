package com.laboratory.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.laboratory.entity.apply.Apply;
import com.laboratory.entity.apply.Price;
import com.laboratory.util.result.CommonResult;
import com.laboratory.util.result.PageResult;
import org.springframework.stereotype.Repository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Repository
public interface ApplyService {
    /**
     * 根据id获取一条用户信息
     * @param id
     * @return
     */
    CommonResult<Apply> selectOneById(String id);
    /**
     * 分页查询用户列表
     *
     * @param currentPage
     * @param queryWrapper
     * @return CommonResult<PageResult < UserWeb>>
     */
    CommonResult<PageResult<Apply>> selectByPage(String currentPage, QueryWrapper<Apply> queryWrapper);

    /**
     * 按条件查询用户列表
     *
     * @param currentPage
     * @param type
     * @param text
     * @return CommonResult<PageResult < UserWeb>>
     */
    CommonResult<PageResult<Apply>> selectByCondition(String currentPage, String type, String text);

    /**
     * 根据id删除用户信息
     *
     * @param id
     * @return CommonResult
     */
    CommonResult deleteOneById(int id);

    /**
     * 根据id批量删除用户
     * @param id
     * @return
     */
    CommonResult deleteBatchById(String[] id);

    /**
     * 插入一个useWeb对象
     * @param apply
     * @return
     */
    CommonResult insertOneUser(Apply apply);

    /**
     * 更新用户信息
     * @param apply
     * @return
     */
    CommonResult updateOneUser(Apply apply);
    /**
     * 返回审批通过设备各个分类的价格
     * @return
     */
    CommonResult<List<Price>> selectPrice();


    void downloadMedicalRecord(String id, HttpServletRequest request, HttpServletResponse response)
            throws IllegalAccessException, IOException;



}
