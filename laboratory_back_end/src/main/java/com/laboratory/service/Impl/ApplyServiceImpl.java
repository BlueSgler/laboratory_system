package com.laboratory.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.laboratory.entity.apply.Apply;
import com.laboratory.entity.apply.Price;
import com.laboratory.mapper.ApplyMapper;
import com.laboratory.service.ApplyService;
import com.laboratory.util.ExportWord;
import com.laboratory.util.result.CommonResult;
import com.laboratory.util.result.PageResult;
import com.laboratory.util.result.ResultCode;
import com.laboratory.util.result.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class ApplyServiceImpl implements ApplyService {
    private static final Long PAGE_SIZE = 10L;

    @Autowired
    ApplyMapper applyMapper;

    @Override
    public CommonResult<Apply> selectOneById(String id) {
        Apply userWeb = applyMapper.selectById(id);
        if (userWeb == null) {
            CommonResult fail = ResultUtil.fail(ResultCode.CUSTOM_FAIL);
            fail.setMessage("没有id为" + id + "的申请记录");
            return fail;
        } else {
            return  ResultUtil.success(userWeb);
        }
    }

    @Override
    public CommonResult<PageResult<Apply>> selectByPage(String currentPage, QueryWrapper<Apply> queryWrapper) {
        if (currentPage == null || "".equals(currentPage)) {
            currentPage = "1";
        }
        Page<Apply> page = new Page<>(Long.parseLong(currentPage), PAGE_SIZE);
        applyMapper.selectPage(page, queryWrapper);
        PageResult<Apply> userWebList = new PageResult<>(page);
        if (userWebList.hasRecords()) {
            return ResultUtil.success(userWebList);
        } else {
            CommonResult fail = ResultUtil.fail(ResultCode.CUSTOM_FAIL);
            fail.setMessage("无记录");
            return fail;
        }
    }

    @Override
    public CommonResult<PageResult<Apply>> selectByCondition(String currentPage, String type, String text) {
        QueryWrapper<Apply> queryWrapper = new QueryWrapper<>();
        if (currentPage == null || "".equals(currentPage)) {
            currentPage = "1";
        }
        if ("id".equals(type)) {
            queryWrapper.eq("id", text);

        } else if ("applicant".equals(type)) {

            queryWrapper.like("applicant", text);
        } else if ("approverName".equals(type)) {
            queryWrapper.like("approver_name", text);
        }else if ("createTime".equals(type)) {
            queryWrapper.like("create_time", text);
        }else if ("updateTime".equals(type)) {
            queryWrapper.like("update_time", text);
        }

        return selectByPage(currentPage, queryWrapper);
    }



    @Override
    public CommonResult deleteOneById(int id) {
        int delete = applyMapper.deleteById(id);
        if (delete == 0) {
            CommonResult fail = ResultUtil.fail(ResultCode.CUSTOM_FAIL);
            fail.setMessage("没有id为" + id + "的申请记录");
            return fail;
        } else {
            return ResultUtil.success();
        }
    }

    @Override
    public CommonResult deleteBatchById(String[] id) {
        for (String s : id) {
            int delete = applyMapper.deleteById(s);
            if (delete == 0) {
                CommonResult fail = ResultUtil.fail(ResultCode.CUSTOM_FAIL);
                return fail;
            }
        }
        return ResultUtil.success();
    }

    @Override
    public CommonResult insertOneUser(Apply userWeb) {
        int insert = applyMapper.insert(userWeb);
        if (insert == 1) {
            return ResultUtil.success();
        } else {
            CommonResult fail = ResultUtil.fail(ResultCode.CUSTOM_FAIL);
            fail.setMessage("插入信息失败");
            return fail;
        }
    }

    @Override
    public CommonResult updateOneUser(Apply userWeb) {
        if (applyMapper.updateById(userWeb) == 1) {
            return ResultUtil.success();
        } else {
            CommonResult fail = ResultUtil.fail(ResultCode.CUSTOM_FAIL);
            fail.setMessage("没有id为" + userWeb.getId() + "的申请记录");
            return fail;
        }
    }

    @Override
    public  CommonResult<List<Price>> selectPrice(){
        List<Price> xx = applyMapper.getTotal();
        if (xx != null) {
            return ResultUtil.success(xx);
        } else {
            CommonResult fail = ResultUtil.fail(ResultCode.CUSTOM_FAIL);
            fail.setMessage("暂无申请记录");
            return fail;
        }
    }

    @Override
    public void downloadMedicalRecord(String id, HttpServletRequest request, HttpServletResponse response){
        Apply apply = applyMapper.selectById(id);
//        SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd");
        Map<String,Object> map = new HashMap<>();
        map.put("name", apply.getName());
        map.put("type", apply.getCategory());
        map.put("price", ""+apply.getPrice());
        map.put("amount", apply.getSpecification());
        map.put("fromFactory", apply.getManufacturer());
        map.put("reason", apply.getReason());
        map.put("applicant", apply.getApplicant());

        ExportWord.exportWord("/申请.docx","/test","第"+id+"申请表.docx",map,request,response);
    }


}
