package com.laboratory.entity.equipment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author echokilig
 * @date 2022/5/28 14:31
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListCondition {

    /**
     * 请求当前页数, 默认1
     */
    private Integer currentPage;

    /**
     * 请求每页的数据条数, 默认10
     */
    private Integer pageSize;

    /**
     * 设备状态, 默认0
     */
    private Integer status;

    /**
     * 表字段
     */
    private String filed;

    /**
     * 字段值
     */
    private String value;

    /**
     * 设备类别
     */
    private String category;

    /**
     * 排序字段
     */
    private String orderFiled;

    /**
     * 排序方式, 0表示升序, 1表示降序(默认)
     */
    private Integer orderWay;

    public static void initParam(ListCondition listCondition) {
        if (listCondition.getCurrentPage() == null) {
            listCondition.setCurrentPage(1);
        }
        if (listCondition.getPageSize() == null) {
            listCondition.setPageSize(10);
        }
        if (listCondition.getOrderFiled() == null){
            listCondition.setOrderFiled("create_time");
        }
        if (listCondition.getOrderWay() == null) {
            listCondition.setOrderWay(1);
        }
    }
}
