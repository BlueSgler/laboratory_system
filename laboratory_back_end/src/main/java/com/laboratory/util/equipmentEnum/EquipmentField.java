package com.laboratory.util.equipmentEnum;

/**
 * @author echokilig
 * @date 2022/5/28 18:00
 */

public enum EquipmentField {
    /**
     * 设备表的字段
     */
    ID("id"),
    EQUIPMENT_NO("equipment_no"),
    NAME("name"),
    MANUFACTURER("manufacturer"),
    SPECIFICATION("specification"),
    NULL("null");

    private String column;

    EquipmentField(String column) {
        this.column = column;
    }

    public String getColumn() {
        return column;
    }

    /**
     * 匹配前端传来的匹配字段名
     * @param field 字段名
     * @return 枚举值
     */
    public static EquipmentField matchField(String field) {
        for (EquipmentField equipmentField : values()) {
            if (equipmentField.name().equalsIgnoreCase(field)) {
                return equipmentField;
            }
        }
        return NULL;
    }
}
