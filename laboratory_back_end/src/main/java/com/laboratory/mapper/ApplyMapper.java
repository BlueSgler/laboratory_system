package com.laboratory.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.laboratory.entity.apply.Apply;
import com.laboratory.entity.apply.Price;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ApplyMapper extends BaseMapper<Apply> {

    @Select("select category as name, SUM(price) value from t_apply group by category")
    List<Price> getTotal();
}
