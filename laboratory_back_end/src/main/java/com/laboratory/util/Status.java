package com.laboratory.util;

import lombok.experimental.Accessors;

/**
 * @author echokilig
 * @date 2022/5/28 13:38
 */

@Accessors(chain = true)
public enum Status {
    /* 正常 */
    NORMAL(0),
    /* 修理 */
    REPAIR(1),
    /* 报废 */
    SCRAP(2);
    private int status;

    Status(int status) {
        this.status = status;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
