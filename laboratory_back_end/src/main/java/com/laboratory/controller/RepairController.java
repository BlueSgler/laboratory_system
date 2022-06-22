package com.laboratory.controller;

import com.alibaba.fastjson.JSONObject;
import com.laboratory.entity.apply.Apply;
import com.laboratory.entity.repair.Repair;
import com.laboratory.entity.repair.RepairPrice;
import com.laboratory.entity.repair.RepairVo;
import com.laboratory.mapper.RepairMapper;
import com.laboratory.service.EquipmentService;
import com.laboratory.service.RepairService;
import com.laboratory.util.Status;
import com.laboratory.util.result.CommonResult;
import com.laboratory.util.result.PageResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/repair")
public class RepairController {
    @Resource
    RepairService repairService;

    @Resource
    EquipmentService equipmentService;
    @Resource
    RepairMapper repairMapper;

    @GetMapping("/select/total")
    CommonResult<List<RepairPrice>> total(){
        return repairService.selectPrice();
    }
//根据设备id查询
    @GetMapping("/select/one/{id}")
    CommonResult<Repair> selectOneById(@PathVariable String id){
        return repairService.selectOneById(id);
    }

    @GetMapping("/select/all")
    CommonResult<PageResult<Repair>> selectByPage(@RequestParam String currentPage) {
        return repairService.selectByPage(currentPage, null);
    }
//按设备id条件和日期分页查询
    @GetMapping("/selectByCondition")
    CommonResult<PageResult<Repair>> selectByCondition(@RequestParam String currentPage,
                                                        @RequestParam String type,
                                                        @RequestParam String text) {

        return repairService.selectByCondition(currentPage, type, text);
    }

    //0528按照id删除用户
    @DeleteMapping("/delete/{id}")
    CommonResult deleteOneById(@PathVariable String id) {
        return repairService.deleteOneById(id);
    }

    @PostMapping(value = "/insert", produces = "application/json;charset=UTF-8")
    CommonResult insertOneUser(@RequestBody Repair repair) {
        /*return repairService.insertOneUser(new Repair(jsonParam.toJavaObject(RepairVo.class)));*/
        equipmentService.modifyEquipmentStatus(String.valueOf(repair.getEquipmentId()), Status.REPAIR);
        return repairService.insertOneUser(repair);
    }

    @PostMapping(value = "/update/{id}", produces = "application/json;charset=UTF-8")
    CommonResult updateOneUser(@RequestBody Repair repair, @PathVariable String id){
        repair.setId(id);
        return repairService.updateOneUser(repair);
    }
    @PostMapping(value = "/save/oneUser", produces = "application/json;charset=UTF-8")
    CommonResult save(@RequestBody JSONObject  json, @RequestParam String id)
    {
        Repair repair=JSONObject.toJavaObject(JSONObject.parseObject(JSONObject.toJSONString(json)),Repair.class);
        equipmentService.modifyEquipmentStatus(id, Status.REPAIR);
        return  repairService.insertOneUser(repair);
    }

}
