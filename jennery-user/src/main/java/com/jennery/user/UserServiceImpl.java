package com.jennery.user;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jennery.blog.common.exception.BizException;
import com.jennery.blog.common.exception.ErrorCode;
import com.jennery.user.bean.User;
import com.jennery.user.mapper.UserMapper;
import com.jennery.user.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    private final UserMapper userMapper;

    @Autowired
    public UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }


    @Override
    public UserVo login(User user) {

        User entity = this.getOne((Wrappers.<User>lambdaQuery().eq(User::getUsername, user.getUsername()).eq(User::getPassword, user.getPassword())));
        if (null == entity) {
            throw new BizException(ErrorCode.FAILED.getCode(), ErrorCode.FAILED.getMsg());
        }
        return new UserVo(entity.getUsername());
    }
}
