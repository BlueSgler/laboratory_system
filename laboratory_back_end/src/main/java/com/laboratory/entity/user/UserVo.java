package com.laboratory.entity.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author hss
 * @date 2022/6/7 13:27
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserVo {

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 真实姓名
     */
    private String name;
}
