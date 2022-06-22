package com.laboratory.entity.userWeb;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author echokilig
 * @date 2022/5/28 13:38
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserWebVo {
    private String username;
    private String password;
    private String name;
    private String sex;
    private String college;
    private String className;
    private String schoolId;
    private String role;
    private int permission;
}
