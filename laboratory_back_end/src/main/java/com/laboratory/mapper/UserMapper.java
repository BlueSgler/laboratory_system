package com.laboratory.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.laboratory.entity.user.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author hss
 * @date 2022/6/7 13:29
 */

@Mapper
public interface UserMapper extends BaseMapper<User> {
}
