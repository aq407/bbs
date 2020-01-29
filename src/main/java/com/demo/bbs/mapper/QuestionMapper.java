package com.demo.bbs.mapper;

import com.demo.bbs.entity.ReplyEntity;
import com.demo.bbs.entity.UserEntity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Mapper
@Service
public interface QuestionMapper {


    @Insert("INSERT INTO `reply` (`reply_id`, `reply_content`, `reply_userid`, `reply_time`, `reply_publicid`, `public_title`, `username`, `avatar`) VALUES (#{replyId}, #{replyContent}, #{replyUserid}, #{replyTime}, #{replyPublicid}, #{publicTitle},#{username},#{avatar});")
    void save(ReplyEntity entity);

    @Select("select * from reply where reply_publicid = #{replyPublicid}")
    List<ReplyEntity> replyList(Long replyPublicid);

    @Select("select username,avatar from sign_up where id = #{id}")
    HashMap<Object,Object> findUser(Long id);

    @Select("select * from reply where reply_id = #{id}")
    List<UserEntity> finAll(Integer id);
}
