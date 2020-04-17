package com.demo.bbs.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Data;
import lombok.NonNull;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Map;

@Data
@TableName("sign_up")
public class UserEntity {
    @TableId(value = "id")
    private long id;

    @NotNull(message = "请输入用户名")
    private String username;

    @NotNull(message = "请输入密码")
    private String password;
    private LocalDateTime signtime;
    private String email;
    private String level = "user";
    private String avatar;//头像


}
