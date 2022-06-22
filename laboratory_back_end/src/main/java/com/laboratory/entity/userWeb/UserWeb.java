package com.laboratory.entity.userWeb;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author echokilig
 * @date 2022/5/28 13:38
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("t_user_web")
public class UserWeb {
    @TableId(type = IdType.AUTO)
    private String id;
    private String username;
    private String password;
    private String name;
    private String sex;
    private String college;
    private String className;
    private String schoolId;
    private String role;
    private int permission;
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    public UserWeb(UserWebVo userWebVo) {
        this.username = userWebVo.getUsername();
        this.password = userWebVo.getPassword();
        this.name = userWebVo.getName();
        this.sex = userWebVo.getSex();
        this.college = userWebVo.getCollege();
        this.className = userWebVo.getClassName();
        this.schoolId = userWebVo.getSchoolId();
        this.role = userWebVo.getRole();
        this.permission = userWebVo.getPermission();
    }
}
