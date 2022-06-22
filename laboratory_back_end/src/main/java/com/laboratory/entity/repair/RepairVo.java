package com.laboratory.entity.repair;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RepairVo {
    private int equipmentId;
    private String repairFactory;
    private int amount;
    private BigDecimal repairPrice;
    private String principal;
}
