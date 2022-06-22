package com.laboratory.entity.equipment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @author echokilig
 * @date 2022/5/28 14:21
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EquipmentVo {
    private String category;
    private String name;
    private String manufacturer;
    private Integer warrantyPeriod;
    private String specification;
    private BigDecimal price;
}
