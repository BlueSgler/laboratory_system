package com.laboratory.entity.apply;

import com.baomidou.mybatisplus.annotation.*;
import com.laboratory.entity.equipment.EquipmentVo;
import lombok.*;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@TableName("t_apply")
public class Apply {
    @TableId(type = IdType.AUTO)
    private String id;
    private String applicant;
    private String approverName;
    private String reason;
    private String applyStatus;
    private String equipId;
    private String category;
    private String name;
    private String manufacturer;
    private Integer warrantyPeriod;
    private String specification;
    private BigDecimal price;

    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    public Apply(String applicant, String approverName, String applyStatus, String id) {
        this.id = id;
        this.applyStatus = applyStatus;
        this.applicant = applicant;
        this.approverName = approverName;
    }

    public EquipmentVo toEquip() {
        EquipmentVo equip = new EquipmentVo();
        equip.setCategory(this.category);
        equip.setManufacturer(this.manufacturer);
        equip.setName(this.name);
        equip.setPrice(this.price);
        equip.setSpecification(this.specification);
        equip.setWarrantyPeriod(this.warrantyPeriod);
        return equip;
    }
}
