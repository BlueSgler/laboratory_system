package com.laboratory.entity.equipment;

import com.baomidou.mybatisplus.annotation.*;
import com.laboratory.util.equipmentEnum.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author echokilig
 * @date 2022/5/28 14:21
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("t_equipment")
public class Equipment {

    /**
     * 自增主键
     */
    @TableId(type = IdType.AUTO)
    private String id;

    /**
     * 设备类别
     */
    private String category;

    /**
     * 设备编号, 根据设备类别+时间戳组成的字符串
     */
    private String equipmentNo;

    /**
     * 设备名
     */
    private String name;

    /**
     * 设备生产厂家
     */
    private String manufacturer;

    /**
     * 设备保修期
     */
    private Integer warrantyPeriod;

    /**
     * 设备规格
     */
    private String specification;

    /**
     * 设备价格
     */
    private BigDecimal price;

    /**
     * 设备状态, 默认填充0(正常运转), 1为维修中, 2为已报废
     */
    @TableField(insertStrategy = FieldStrategy.NEVER)
    private Integer status;

    /**
     * 字段生成时间, 作为购置日期
     */
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    /**
     * 修改时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    public Equipment(EquipmentVo equipmentVo) {
        this.category = equipmentVo.getCategory();
        this.equipmentNo = Category.getShortNameByFullName(this.category) + System.currentTimeMillis();
        this.name = equipmentVo.getName();
        this.manufacturer = equipmentVo.getManufacturer();
        this.warrantyPeriod = equipmentVo.getWarrantyPeriod();
        this.specification = equipmentVo.getSpecification();
        this.price = equipmentVo.getPrice();
    }
}
