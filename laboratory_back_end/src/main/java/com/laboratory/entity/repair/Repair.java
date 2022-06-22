package com.laboratory.entity.repair;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.math.BigDecimal;
import java.util.Date;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@TableName("t_repair")
@Builder
public class Repair {
    
    @TableId(type = IdType.AUTO)
    private String id;
    private int equipmentId;
    private String equipmentName;
    private String repairFactory;
    private BigDecimal repairPrice;
    private String principal;
    private boolean status;
    private Date repairTime;
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
    public Repair(RepairVo repairVo){
        this.equipmentId=repairVo.getEquipmentId();
        this.repairFactory=repairVo.getRepairFactory();
        this.repairPrice=repairVo.getRepairPrice();
        this.principal=repairVo.getPrincipal();
    }
}
