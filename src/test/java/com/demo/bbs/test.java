package com.demo.bbs;

import com.demo.bbs.entity.ReplyEntity;
import com.demo.bbs.entity.UserEntity;
import com.demo.bbs.mapper.QuestionMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;

public class test {
    @Autowired
    private QuestionMapper questionMapper;

    @Test
    public void test(){

        List<ReplyEntity> list = questionMapper.replyList(1L);

        System.out.println(list);
    }

}
