package com.demo.bbs.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("reply")
public class ReplyEntity {

    @TableId(value = "replyId")
    private Long replyId;
    private String replyContent;
    private Long replyUserid;
    private LocalDateTime replyTime;
    private Long replyPublicid;
    private String publicTitle;
    private String username;
    private String avatar;

}
