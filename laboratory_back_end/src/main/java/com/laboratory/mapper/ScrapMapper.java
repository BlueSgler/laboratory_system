package com.laboratory.mapper;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.laboratory.entity.scrap.Scrap;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface ScrapMapper extends BaseMapper<Scrap> {
    @Select("select id from t_equipment where equipment_no = #{equipmentNo}")
    String getId(String equipmentNo);
}
