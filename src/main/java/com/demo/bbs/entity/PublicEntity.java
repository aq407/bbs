package com.demo.bbs.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("public")
public class PublicEntity {

    @TableId(value = "Id")
    private long id;
    private String title;
    private String covers; //封面
    private String tag; //标签
    private String user; //发表用户
    private LocalDateTime time; //发表时间
    private String content;
    private String synopsis; //简介
    //private String htmlContent;

}
