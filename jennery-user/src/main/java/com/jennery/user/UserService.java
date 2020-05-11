package com.jennery.user;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jennery.user.bean.User;
import com.jennery.user.vo.UserVo;

public interface UserService extends IService<User> {


    UserVo login(User user);
}
