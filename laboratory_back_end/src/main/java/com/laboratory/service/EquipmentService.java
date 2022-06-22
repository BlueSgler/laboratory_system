package com.laboratory.service;

import com.alibaba.fastjson.JSONObject;
import com.laboratory.entity.equipment.Equipment;
import com.laboratory.entity.equipment.EquipmentCounter;
import com.laboratory.util.Status;
import com.laboratory.util.result.CommonResult;
import com.laboratory.util.result.PageResult;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author echokilig
 * @date 2022/5/28 13:37
 */
@Repository
public interface EquipmentService {

    /**
     * 根据id获取设备信息
     * @param id
     * @return CommonResult<Equipment>
     */
    CommonResult<Equipment> selectOneById(String id);

    /**
     * 插入一条设备信息, 申请购买时调用
     * @param jsonObject
     * 包括分类名(category), 设备名(name), 生产厂家(manufacturer), 保修期(warrantyPeriod), 设备规格(specification), 价格(price)
     * 分类名不能随机生成, 否则会报错, 测试的话可以在util.Category里的fullName值拿来测试
     * @return String 失败为null, 成功为插入的id
     */
    String insertOne(JSONObject jsonObject);

    /**
     * 在进行申请, 修理, 报废时要修改设备的状态
     * @param id 修改的设备id
     * @param status 设备状态, 使用util里面的枚举类Status
     * @return int 1为成功, 0为失败
     * 示例 (申请通过后): equipmentService.modifyEquipmentStatus(id, Status.APPLY_ACCEPT);
     */
    int modifyEquipmentStatus(String id, Status status);

    /**
     * 根据id修改设备信息
     * @param id 设备id
     * @param jsonObject 前端封装成的EquipmentVo的Json对象
     * @return CommonResult
     */
    CommonResult modifyOneById(String id, JSONObject jsonObject);

    /**
     * 查询设备列表
     * @param jsonObject 存储查询条件
     * @return 返回符合条件的设备列表
     */
    CommonResult<PageResult<Equipment>> getEquipmentList(JSONObject jsonObject);

    /**
     * 获取设备数量
     * @param field 统计字段
     * @return CommonResult<List<EquipmentCounter>>
     */
    CommonResult<List<EquipmentCounter>> getEquipNumbers(String field);
}
