package com.laboratory.entity.equipment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author echokilig
 * @date 2022/6/4 16:19
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EquipmentCounter {
    private String name;
    private Integer value;
}
