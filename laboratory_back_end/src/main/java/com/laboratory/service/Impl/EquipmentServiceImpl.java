package com.laboratory.service.Impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.laboratory.entity.equipment.Equipment;
import com.laboratory.entity.equipment.EquipmentCounter;
import com.laboratory.entity.equipment.EquipmentVo;
import com.laboratory.entity.equipment.ListCondition;
import com.laboratory.mapper.EquipmentMapper;
import com.laboratory.service.EquipmentService;
import com.laboratory.util.equipmentEnum.EquipmentField;
import com.laboratory.util.Status;
import com.laboratory.util.result.CommonResult;
import com.laboratory.util.result.PageResult;
import com.laboratory.util.result.ResultCode;
import com.laboratory.util.result.ResultUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;

/**
 * @author echokilig
 * @date 2022/5/28 13:37
 */

@Service
public class EquipmentServiceImpl implements EquipmentService {
    private static String CATEGORY = "category";
    private static String STATUS = "status";
    @Resource
    EquipmentMapper equipmentMapper;

    @Override
    public CommonResult<Equipment> selectOneById(String id) {
        Equipment equipment = equipmentMapper.selectById(id);
        if (equipment != null) {
            return ResultUtil.success(equipment);
        } else {
            return ResultUtil.fail("未查询到id为" + id + "设备");
        }
    }


    @Override
    public String insertOne(JSONObject jsonObject) {
        Equipment equipment = new Equipment(JSONObject.toJavaObject(jsonObject, EquipmentVo.class));
        return equipmentMapper.insert(equipment) == 1
                ? equipmentMapper.getId(equipment.getEquipmentNo())
                : null;
    }

    @Override
    public int modifyEquipmentStatus(String id, Status status) {
        return equipmentMapper.update(null, new UpdateWrapper<Equipment>().eq("id", id).set("status", status.getStatus()));
    }

    @Override
    public CommonResult modifyOneById(String id, JSONObject jsonObject) {
        EquipmentVo equipmentVo = JSONObject.toJavaObject(jsonObject, EquipmentVo.class);
        UpdateWrapper<Equipment> updateWrapper = new UpdateWrapper<Equipment>().eq("id", id)
                .set(equipmentVo.getCategory() != null, "category", equipmentVo.getCategory())
                .set(equipmentVo.getName() != null, "name", equipmentVo.getName())
                .set(equipmentVo.getManufacturer() != null, "manufacturer", equipmentVo.getManufacturer())
                .set(equipmentVo.getWarrantyPeriod() != null, "warranty_period", equipmentVo.getWarrantyPeriod())
                .set(equipmentVo.getSpecification() != null, "specification", equipmentVo.getSpecification())
                .set(equipmentVo.getPrice() != null, "price", equipmentVo.getPrice());
        return equipmentMapper.update(null, updateWrapper) == 0
                ? ResultUtil.fail(ResultCode.COMMON_FAIL)
                : ResultUtil.success();
    }

    @Override
    public CommonResult<PageResult<Equipment>> getEquipmentList(JSONObject jsonObject) {
        ListCondition listCondition = jsonObject.toJavaObject(ListCondition.class);
        ListCondition.initParam(listCondition);
        QueryWrapper<Equipment> queryWrapper = new QueryWrapper<>();
        Page<Equipment> page = new Page<>(listCondition.getCurrentPage(), listCondition.getPageSize());
//        设置查询的设备状态
        if (listCondition.getStatus() != null) {
            queryWrapper.eq("status", listCondition.getStatus());
        }
//        匹配搜索字段查询
        String value = listCondition.getValue();
        switch (Objects.requireNonNull(EquipmentField.matchField(listCondition.getFiled()))) {
            case ID:
//                id等值匹配
                queryWrapper.eq(EquipmentField.ID.getColumn(), value);
                break;
            case EQUIPMENT_NO:
//                equipmentNo等值匹配
                queryWrapper.eq(EquipmentField.EQUIPMENT_NO.getColumn(), value);
                break;
            case NAME:
//                name模糊匹配
                queryWrapper.like(EquipmentField.NAME.getColumn(), value);
                break;
            case MANUFACTURER:
//                manufacturer模糊匹配
                queryWrapper.like(EquipmentField.MANUFACTURER.getColumn(), value);
                break;
            case SPECIFICATION:
//                specification模糊匹配
                queryWrapper.like(EquipmentField.SPECIFICATION.getColumn(), value);
                break;
            default:
        }
//        设置查询的设备类别
        if (listCondition.getCategory() != null) {
            queryWrapper.eq("category", listCondition.getCategory());
        }
//        设置排序字段及方式
        String orderFiled = listCondition.getOrderFiled();
        if (listCondition.getOrderWay() == 1) {
            queryWrapper.orderByDesc(orderFiled);
        } else {
            queryWrapper.orderByAsc(orderFiled);
        }
        equipmentMapper.selectPage(page, queryWrapper);
        PageResult<Equipment> pageResult = new PageResult<>(page);
        return pageResult.hasRecords() ? ResultUtil.success(pageResult) : ResultUtil.fail("未查询到设备记录");
    }

    @Override
    public CommonResult<List<EquipmentCounter>> getEquipNumbers(String field) {
//        防止sql注入
        if (!CATEGORY.equals(field) && !STATUS.equals(field)) {
            return ResultUtil.fail("非法字段");
        }
        List<EquipmentCounter> list = equipmentMapper.getNumber(field);
        if (STATUS.equals(field)) {
            for (EquipmentCounter equipmentCounter : list) {
                switch (equipmentCounter.getName()) {
                    case "0":
                        equipmentCounter.setName("正常");
                        break;
                    case "1":
                        equipmentCounter.setName("维修中");
                        break;
                    case "2":
                        equipmentCounter.setName("已报废");
                        break;
                    default:
                }
            }
        }
        return list == null ? ResultUtil.fail("统计失败") : ResultUtil.success(list);
    }
}
