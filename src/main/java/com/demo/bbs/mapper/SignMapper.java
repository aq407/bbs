package com.demo.bbs.mapper;

import com.demo.bbs.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Service;

import java.util.HashMap;

/**登录注册mapper
 * @author Li
 *
 */

@Mapper
@Service
public interface SignMapper {

     /**插入注册信息
      * @param user 用户信息
      */
     void inserUser(UserEntity user);

     /**
      * 根据用户名查询密码
      * @param username 根据用户名查询
      *
      * @return
      */
     HashMap findAll(String username);

}
