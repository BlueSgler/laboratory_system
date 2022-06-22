package com.laboratory.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.laboratory.entity.equipment.Equipment;
import com.laboratory.entity.equipment.EquipmentCounter;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author echokilig
 * @date 2022/5/28 13:38
 */

@Mapper
public interface EquipmentMapper extends BaseMapper<Equipment> {
    /**
     * 根据设备编号(唯一)获取设备主键
     * @param equipmentNo 设备编号
     * @return 设备id
     */
    @Select("select id from t_equipment where equipment_no = #{equipmentNo}")
    String getId(String equipmentNo);

    /**
     * 按照分类或者设备状态获取数量
     * @param filed 统计字段
     * @return List<EquipmentCounter>
     */
    @Select("SELECT ${field} name, count(*) value FROM t_equipment group by ${field}")
    List<EquipmentCounter> getNumber(String filed);
}
