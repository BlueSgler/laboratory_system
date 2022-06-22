package com.laboratory.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.laboratory.entity.repair.Repair;
import com.laboratory.entity.repair.RepairPrice;
import com.laboratory.mapper.RepairMapper;
import com.laboratory.service.RepairService;
import com.laboratory.util.result.CommonResult;
import com.laboratory.util.result.PageResult;
import com.laboratory.util.result.ResultCode;
import com.laboratory.util.result.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RepairServiceImpl implements RepairService {
    private static final Long PAGE_SIZE = 10L;

    @Autowired
    RepairMapper repairMapper;

    @Override
    public CommonResult<Repair> selectOneById(String equipmentID) {
        Repair repair = repairMapper.selectById(equipmentID);
        if (repair == null) {
            CommonResult fail = ResultUtil.fail(ResultCode.CUSTOM_FAIL);
            fail.setMessage("没有设备id为" + equipmentID + "的用户");
            return fail;
        } else {
            return ResultUtil.success(repair);
        }
    }

    @Override
    public CommonResult<PageResult<Repair>> selectByPage(String currentPage, QueryWrapper<Repair> queryWrapper) {
        if (currentPage == null || "".equals(currentPage)) {
            currentPage = "1";
        }
        Page<Repair> page = new Page<>(Long.parseLong(currentPage), PAGE_SIZE);
        repairMapper.selectPage(page, queryWrapper);
        PageResult<Repair> repairList = new PageResult<>(page);
        if (repairList.hasRecords()) {
            return ResultUtil.success(repairList);
        } else {
            CommonResult fail = ResultUtil.fail(ResultCode.CUSTOM_FAIL);
            fail.setMessage("无记录");
            return fail;
        }
    }

    @Override
    public CommonResult<PageResult<Repair>> selectByCondition(String currentPage, String type, String text) {
        QueryWrapper<Repair> queryWrapper = new QueryWrapper<>();
        if (currentPage == null || "".equals(currentPage)) {
            currentPage = "1";
        }
        if ("id".equals(type)) {
            queryWrapper.eq("id", text);
        } else if("status".equals(type)){
            queryWrapper.eq("status",text);
        } else if ("equipmentId".equals(type)) {
            queryWrapper.eq("equipment_id", text);
        } else if("equipmentName".equals(type)){
            queryWrapper.like("equipment_name",text);
        } else if ("repairFactory".equals(type)) {
            queryWrapper.like("repair_factory", text);
        } else if ("repairPrice".equals(type)) {
            queryWrapper.like("repair_price", text);
        } else if ("principal".equals(type)) {
            queryWrapper.like("principal", text);
        } else if("createTime".equals(type)){
            queryWrapper.like("create_time",text);
        } else if("updateTime".equals(type)){
            queryWrapper.like("update_time",text);
        } else if("repairTime".equals(type)){
            queryWrapper.like("repair_time",text);
        }
        return selectByPage(currentPage, queryWrapper);
    }

    @Override
    public CommonResult deleteOneById(String id) {
        int delete = repairMapper.deleteById(id);
        if (delete == 0) {
            CommonResult fail = ResultUtil.fail(ResultCode.CUSTOM_FAIL);
            fail.setMessage("没有id为" + id + "的商品");
            return fail;
        } else {
            return ResultUtil.success();
        }
    }


    @Override
    public CommonResult insertOneUser(Repair repair) {
        int insert = repairMapper.insert(repair);
        if (insert == 1) {
            return ResultUtil.success();
        } else {
            CommonResult fail = ResultUtil.fail(ResultCode.CUSTOM_FAIL);
            fail.setMessage("插入用户信息失败");
            return fail;
        }
    }

    @Override
    public CommonResult updateOneUser(Repair repair) {
        if (repairMapper.updateById(repair) == 1) {
            return ResultUtil.success();
        } else {
            CommonResult fail = ResultUtil.fail(ResultCode.CUSTOM_FAIL);
            fail.setMessage("没有id为" + repair.getId() + "的用户");
            return fail;
        }
    }

    @Override
    public  CommonResult<List<RepairPrice>> selectPrice(){
        List<RepairPrice> price = repairMapper.getPrice();
        if (price != null) {
            return ResultUtil.success(price);
        } else {
            CommonResult fail = ResultUtil.fail(ResultCode.CUSTOM_FAIL);
            fail.setMessage("暂无申请记录");
            return fail;
        }
    }
}
