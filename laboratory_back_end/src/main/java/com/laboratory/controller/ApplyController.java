package com.laboratory.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.laboratory.entity.apply.Apply;
import com.laboratory.entity.apply.Price;
import com.laboratory.entity.equipment.Equipment;

import com.laboratory.mapper.ApplyMapper;
import com.laboratory.mapper.EquipmentMapper;
import com.laboratory.service.ApplyService;

import com.laboratory.util.result.CommonResult;
import com.laboratory.util.result.PageResult;

import com.laboratory.util.result.ResultCode;
import com.laboratory.util.result.ResultUtil;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.List;


@RestController
@RequestMapping("/apply")
public class ApplyController {

    @Resource
    ApplyMapper applyMapper;

    @Resource
    ApplyService applyService;

    @Resource
    EquipmentMapper equipmentMapper;

//    @GetMapping("/select/oneUser/{id}")
//    CommonResult<Apply> selectOneById(@PathVariable String id){
//        return applyService.selectOneById(id);
//    }

    @GetMapping("/select/all")
    CommonResult<PageResult<Apply>> selectByPage(@RequestParam String currentPage) {
        return applyService.selectByPage(currentPage, null);
    }

    @GetMapping("/select/condition")
    CommonResult<PageResult<Apply>> selectByCondition(@RequestParam String currentPage,
                                                        @RequestParam String type,
                                                        @RequestParam String text) {

        return applyService.selectByCondition(currentPage, type, text);
    }

    @DeleteMapping("/delete/oneUser/{id}")
    CommonResult deleteOneById(@PathVariable int id) {
        return applyService.deleteOneById(id);
    }

    @GetMapping("/delete/batchUser")
    CommonResult deleteBatchById(@RequestParam String deleteId) {
        String[] deleteIds = deleteId.split(",");
        return applyService.deleteBatchById(deleteIds);
    }

//    @PostMapping(value = "/insert/oneUser", produces = "application/json;charset=UTF-8")
////    CommonResult insertOneUser(@RequestBody JSONObject jsonParam) {
////        return userWebService.insertOneUser(new Apply(jsonParam.toJavaObject(ApplyVo.class)));
////    }
//    CommonResult insertOneUser(@RequestBody Apply apply) {
//        return applyService.insertOneUser(apply);
//    }
//
//    @PostMapping(value = "/update/oneUser/{id}", produces = "application/json;charset=UTF-8")
////    CommonResult updateOneUser(@RequestBody JSONObject jsonParam, @PathVariable BigInteger id){
////        Apply apply = new Apply(jsonParam.toJavaObject(ApplyVo.class));
////        apply.setId(id);
////        return userWebService.updateOneUser(apply);
////    }
//    CommonResult updateOneUser(@RequestBody Apply apply, @PathVariable String id){
//        apply.setId(id);
//        return applyService.updateOneUser(apply);
//    }

    @PostMapping(value = "/save/oneUser", produces = "application/json;charset=UTF-8")
//    CommonResult save(@RequestBody JSONObject jsonParam,
//                      @RequestParam String applicant,
//                      @RequestParam String approverName,
//                      @RequestParam String id,
//                      @RequestParam String applyStatus
////                      @RequestParam String equipName,
////                      @RequestParam String equipType,
////                      @RequestParam String specification
//                      )
//    {
//        if("".equals(applyStatus) || applyStatus == null)
//        {
//            applyStatus = "2";
//        }
//
//        Apply apply = new Apply(applicant,approverName,applyStatus,id);
//        if(apply.getId() != null && "" != apply.getId())
//        {
//
//            if("0".equals(apply.getApplyStatus()))
//            {
//                int a = equipmentService.modifyEquipmentStatus(applyMapper.selectById(apply.getId()).getEquipId(), Status.APPLY_ACCEPT);
//                if(0==a)
//                {
//                    CommonResult fail = ResultUtil.fail(ResultCode.CUSTOM_FAIL);
//                    fail.setMessage("id为" + applyMapper.selectById(apply.getId()).getEquipId() + "的设备状态修改为0时失败");
//                    return fail;
//                }
//            }
//            else if("4".equals(apply.getApplyStatus()))
//            {
//                int a = equipmentService.modifyEquipmentStatus(applyMapper.selectById(apply.getId()).getEquipId(),Status.APPLY_REJECT);
//                if(0==a)
//                {
//                    CommonResult fail = ResultUtil.fail(ResultCode.CUSTOM_FAIL);
//                    fail.setMessage("id为" + applyMapper.selectById(apply.getId()).getEquipId() + "的设备状态修改为4时失败");
//                    return fail;
//                }
//
//            }
//            return  applyService.updateOneUser(apply);
//        }
//        else
//        {
//            apply.setEquipId(equipmentService.insertOne(jsonParam));
//            return  applyService.insertOneUser(apply);
//        }
//    }
//}
    CommonResult save(@RequestBody Apply apply)
    {
        if("".equals(apply.getApplyStatus()) || apply.getApplyStatus() == null)
        {
            apply.setApplyStatus("0");
        }
        if(apply.getId() != null && "" != apply.getId())
        {
            Apply yy = applyMapper.selectById(apply.getId());
            if("1".equals(apply.getApplyStatus()) && !("1".equals(yy.getApplyStatus())))
            {
                Equipment xx = new Equipment(yy.toEquip());
                equipmentMapper.insert(xx);
                apply.setEquipId(equipmentMapper.getId(xx.getEquipmentNo()));
            }
            return  applyService.updateOneUser(apply);
        }
        else
        {
            return  applyService.insertOneUser(apply);
        }
    }

    @GetMapping("/select/total")
    CommonResult<List<Price>> total(){
        return applyService.selectPrice();
    }

    @RequestMapping("/download")
    public CommonResult downloadMedicalRecord(@RequestParam String id, HttpServletRequest request, HttpServletResponse response) {
        try {
            applyService.downloadMedicalRecord(id, request, response);
        } catch (Exception e) {
            CommonResult fail = ResultUtil.fail(ResultCode.CUSTOM_FAIL);
            fail.setMessage("下载失败");
            return fail;
        }
        return ResultUtil.success();
    }


}
