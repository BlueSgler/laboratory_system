package com.laboratory.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.laboratory.entity.scrap.Scrap;
import com.laboratory.util.result.CommonResult;
import com.laboratory.util.result.PageResult;
import org.springframework.stereotype.Repository;

@Repository
public interface ScrapService {
    /**
     * 根据id获取一条用户信息
     * @param id
     * @return
     */
    CommonResult<Scrap> selectOneById(String id);
    /**
     * 分页查询用户列表
     *
     * @param currentPage
     * @param queryWrapper
     * @return CommonResult<PageResult < UserWeb>>
     */
    CommonResult<PageResult<Scrap>> selectByPage(String currentPage, QueryWrapper<Scrap> queryWrapper);

    /**
     * 按条件查询用户列表
     *
     * @param currentPage
     * @param type
     * @param text
     * @return CommonResult<PageResult < UserWeb>>
     */
    CommonResult<PageResult<Scrap>> selectByCondition(String currentPage, String type, String text);

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
     * @param scrap
     * @return
     */
    CommonResult insertOneUser(Scrap scrap);

    /**
     * 更新用户信息
     * @param scrap
     * @return
     */
    CommonResult updateOneUser(Scrap scrap);
}
