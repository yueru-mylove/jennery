package com.jennery.user;

import com.jennery.blog.common.bean.Result;
import com.jennery.blog.common.exception.BizException;
import com.jennery.blog.common.exception.ErrorCode;
import com.jennery.user.bean.User;
import com.jennery.user.vo.UserVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@RequestMapping("/api/user")
@RestController
@Slf4j
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


    @PostMapping("/login")
    public Result<UserVo> login(@RequestBody @Validated User user) {
        /*if (bindingResult.hasErrors()) {
            log.info("[请求异常]客户端参数错误 ==> [{}]", bindingResult.getAllErrors().stream().map(ObjectError::getDefaultMessage).collect(Collectors.joining(",")));
            throw new BizException(ErrorCode.FAILED.getCode(), bindingResult.getAllErrors().get(0).getDefaultMessage());
        }*/
        return Result.success(userService.login(user));
    }


}
