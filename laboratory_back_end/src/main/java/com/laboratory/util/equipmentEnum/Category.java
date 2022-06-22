package com.laboratory.util.equipmentEnum;

import lombok.experimental.Accessors;

/**
 * @author echokilig
 * @date 2022/5/28 13:38
 */

@Accessors(chain = true)
public enum Category {
    /*设备缩写*/
    SOFTWARE_ENGINEERING("软件工程实验室设备", "SELEL"),
    SERVICE_ENGINEERING_AND_APPLICATION("服务工程与应用实验室设备", "SEAL"),
    EMBEDDED_SOFTWARE("嵌入式软件实验室设备", "ESL"),
    CLOUD_COMPUTING_TECHNOLOGY("云计算技术实验室设备", "CCTL")
    ,
    SOFTWARE_USABILITY("软件可用性实验室设备", "SUL"),
    MOBILE_INTERNET_TECHNOLOGY("移动互联网技术实验室设备", "MITL"),
    OUTSOURCING("服务外包实验室设备", "OL");

    private String fullName;
    private String shortName;

    Category() {
    }

    Category(String fullName, String shortName) {
        this.fullName = fullName;
        this.shortName = shortName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public static String getShortNameByFullName(String fullName){
        for (Category category : Category.values()){
            if (category.getFullName().equals(fullName)){
                return category.getShortName();
            }
        }
        return null;
    }
}
