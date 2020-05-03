package com.demo.bbs.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.demo.bbs.entity.PublicEntity;
import com.demo.bbs.entity.UserEntity;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * @author Li
 */
@Mapper
@Service
public interface UserMapper extends BaseMapper<UserEntity> {

    /**
     * 查询用户所发的贴
     * @param user 用户名
     * @return 返回相关的帖子
     */
    @Select("select * from public where user = #{user}")
    List<PublicEntity> post(@Param("user") String user);


    /**
     * 根据id来删除帖子
     * @param id 贴子的id
     */
    @Delete("delete from public where id = #{id}")
    void deletePublic(Long id);

    /**
     * 根据id来查询所有的帖子
     * @param id 帖子id
     * @return 反回hashmap
     */
    @Select("select * from public where id = #{id}")
    HashMap<Object,Object> findId(Long id);

    /**
     * 注册查询用户名有没有重复
     * @param username 用户名
     * @return 返回查询到的用户名
     */
    @Select("select * from sign_up where username = #{username}")
    String userName(String username);



}
