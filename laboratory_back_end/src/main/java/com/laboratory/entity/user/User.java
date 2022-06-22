package com.laboratory.entity.user;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author hss
 * @date 2022/6/7 13:23
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("t_user")
public class User {

    /**
     * 自增主键
     */
    @TableId(type = IdType.AUTO)
    private String id;

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

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;


    /**
     * 修改时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    public User(UserVo userVo) {
        this.username = userVo.getUsername();
        this.password = userVo.getPassword();
        this.name = userVo.getName();
    }
}
