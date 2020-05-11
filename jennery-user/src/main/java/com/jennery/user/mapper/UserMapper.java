package com.jennery.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jennery.user.bean.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface UserMapper extends BaseMapper<User> {
}
