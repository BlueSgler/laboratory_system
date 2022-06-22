package com.laboratory.service;

import com.laboratory.entity.repair.Repair;
import com.laboratory.entity.repair.RepairPrice;
import com.laboratory.util.result.CommonResult;
import com.laboratory.util.result.PageResult;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 */
@Repository
public interface RepairService {

    /**
     * 根据设备id获取一条用户信息
     * @param equipmentID
     * @return
     */
    CommonResult<Repair> selectOneById(String equipmentID);
    /**
     * 分页查询用户列表
     *
     * @param currentPage
     * @param queryWrapper
     * @return CommonResult<PageResult < UserWeb>>
     */
    CommonResult<PageResult<Repair>> selectByPage(String currentPage, QueryWrapper<Repair> queryWrapper);

    /**0531
     * 按条件查询用户列表，根据Id精确查找和根据日期模糊查找
     *
     * @param currentPage
     * @param type
     * @param text
     * @return CommonResult<PageResult < UserWeb>>
     */
    CommonResult<PageResult<Repair>> selectByCondition(String currentPage, String type, String text);

    /**
     * 根据id删除用户信息
     *0528
     * @param id
     * @return CommonResult
     */
    CommonResult deleteOneById(String id);


    /**
     * 插入一个repair对象
     * @param repair
     * @return
     */
    CommonResult insertOneUser(Repair repair);

    /**
     * 更新用户信息
     * @param repair
     * @return
     */
    CommonResult updateOneUser(Repair repair);
    /**
     * 查询修理价格
     * @param
     * @return
     */
    CommonResult<List<RepairPrice>> selectPrice();
}
