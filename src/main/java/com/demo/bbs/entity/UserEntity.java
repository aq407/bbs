package com.demo.bbs.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("sign_up")
public class UserEntity {
    @TableId(value = "id")
    private long id;
    private String username;
    private String password;
    private LocalDateTime signtime;
    private String email;
    private String level = "user";
    private String avatar;//头像
}
