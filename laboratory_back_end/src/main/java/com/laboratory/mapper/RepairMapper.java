package com.laboratory.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.laboratory.entity.repair.Repair;
import com.laboratory.entity.repair.RepairPrice;
import com.laboratory.entity.userWeb.UserWeb;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;


/**
 * @author 23890
 */
@Mapper
public interface RepairMapper extends BaseMapper<Repair> {
    @Select("select  sum(repair_price) as value,MONTH(repair_time) as name from " +
            "t_repair group by Month(repair_time)")
    List<RepairPrice> getPrice();
}
