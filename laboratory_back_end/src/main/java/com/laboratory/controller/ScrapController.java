package com.laboratory.controller;

import com.alibaba.fastjson.JSONObject;
import com.laboratory.entity.scrap.Scrap;
import com.laboratory.mapper.ScrapMapper;
import com.laboratory.service.EquipmentService;
import com.laboratory.service.ScrapService;
import com.laboratory.util.Status;
import com.laboratory.util.result.CommonResult;
import com.laboratory.util.result.PageResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/scrap")
public class ScrapController {

    @Resource
    ScrapService scrapService;

    @Resource
    ScrapMapper scrapMapper;
    @Resource
    EquipmentService equipmentService;
    @GetMapping("/select/oneUser/{id}")
    CommonResult<Scrap> selectOneById(@PathVariable String id){
        return scrapService.selectOneById(id);
    }

    @GetMapping("/select/all")
    CommonResult<PageResult<Scrap>> selectByPage(@RequestParam String currentPage) {
        return scrapService.selectByPage(currentPage, null);
    }

    @GetMapping("/select/condition")
    CommonResult<PageResult<Scrap>> selectByCondition(@RequestParam String currentPage,
                                                        @RequestParam String type,
                                                        @RequestParam String text) {

        return scrapService.selectByCondition(currentPage, type, text);
    }

    @DeleteMapping("/delete/oneUser/{id}")
    CommonResult deleteOneById(@PathVariable String id) {
        return scrapService.deleteOneById(id);
    }

    @GetMapping("/delete/batchUser")
    CommonResult deleteBatchById(@RequestParam String deleteId) {
        String[] deleteIds = deleteId.split(",");
        return scrapService.deleteBatchById(deleteIds);
    }

    @PostMapping(value ="/insert/oneUser",produces = "application/json;charset=UTF-8")
    CommonResult insertOneUser(@RequestBody Scrap scrap){
        equipmentService.modifyEquipmentStatus(scrap.getEquipmentId(), Status.SCRAP);
        return scrapService.insertOneUser(scrap);

    }

    @PostMapping(value = "/update/oneUser/{id}",produces = "application/json;charset=UTF-8")
    CommonResult updateOneUser(@RequestBody Scrap scrap ,@PathVariable String id){
        //Scrap scrap=JSONObject.toJavaObject(JSONObject.parseObject(JSONObject.toJSONString(json)),Scrap.class);
        scrap.setId(id);
        return scrapService.updateOneUser(scrap);
    }
    @PostMapping(value = "/save/oneUser", produces = "application/json;charset=UTF-8")
    CommonResult save(@RequestBody JSONObject  json, @RequestParam String id)
    {
        System.out.println(id);
        Scrap scrap=JSONObject.toJavaObject(JSONObject.parseObject(JSONObject.toJSONString(json)),Scrap.class);
        equipmentService.modifyEquipmentStatus(id, Status.SCRAP);
        return  scrapService.insertOneUser(scrap);
    }
}
