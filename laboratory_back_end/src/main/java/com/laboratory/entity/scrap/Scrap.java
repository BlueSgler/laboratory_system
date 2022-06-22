package com.laboratory.entity.scrap;

import com.baomidou.mybatisplus.annotation.*;
import lombok.*;

import java.sql.Timestamp;
import java.util.Date;
import java.math.BigDecimal;

@Setter
@Getter
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("t_scrap")
@Builder
public class Scrap {
    @TableId(value = "id",type = IdType.AUTO)
    private String id;
    private String handler;
    private String reason;
    private String equipmentId;
    private String name;
    private String category;
    private String specification;
    private Date scrapTime;
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;
    public Scrap(String id, String handler, String reason, String equipmentId)
    {
        this.id=id;
        this.equipmentId=equipmentId;
        this.reason=reason;
        this.handler=handler;
    }
}
