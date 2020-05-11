package com.jennery.user.bean;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
public class User {

    @NotNull(message = "ERROR_USERNAME_EMPTY")
    @NotBlank(message = "ERROR_USERNAME_EMPTY")
    private String username;


    @NotNull(message = "ERROR_PASSWORD_EMPTY")
    @NotBlank(message = "ERROR_PASSWORD_EMPTY")
    private String password;

    @TableField(exist = false)
    private Date lastLoginTime;

    @TableField(exist = false)
    private String lastLoginRegion;
}
