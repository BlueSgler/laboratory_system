package com.laboratory.controller;

import com.alibaba.fastjson.JSONObject;
import com.laboratory.entity.equipment.Equipment;
import com.laboratory.entity.equipment.EquipmentCounter;
import com.laboratory.entity.equipment.EquipmentVo;
import com.laboratory.service.EquipmentService;
import com.laboratory.util.result.CommonResult;
import com.laboratory.util.result.PageResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author echokilig
 * @date 2022/5/28 13:37
 */

@RestController
@RequestMapping("/equipment")
public class EquipmentController {
    @Resource
    EquipmentService equipmentService;

    @GetMapping("/get/one/{id}")
    CommonResult<Equipment> selectOneById(@PathVariable String id) {
        return equipmentService.selectOneById(id);
    }

    @PostMapping(value = "/insert/one", produces = "application/json;charset=UTF-8")
    String insertOne(@RequestBody JSONObject jsonParam) {
        EquipmentVo equipmentVo = JSONObject.toJavaObject(jsonParam, EquipmentVo.class);
        return equipmentService.insertOne(jsonParam);
    }

    @PutMapping(value = "/modify/one/{id}", produces = "application/json;charset=UTF-8")
    CommonResult modifyOneById(@PathVariable String id, @RequestBody JSONObject jsonObject) {
        return equipmentService.modifyOneById(id, jsonObject);
    }

    @PostMapping(value = "/get/list", produces = "application/json;charset=UTF-8")
    CommonResult<PageResult<Equipment>> getEquipmentList(@RequestBody JSONObject jsonObject) {
        return equipmentService.getEquipmentList(jsonObject);
    }

    @GetMapping("/get/numbers")
    CommonResult<List<EquipmentCounter>> getEquipNumbers(@RequestParam String field){
        return equipmentService.getEquipNumbers(field);
    }
}
