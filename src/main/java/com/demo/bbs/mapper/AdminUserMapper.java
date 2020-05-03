package com.demo.bbs.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.demo.bbs.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Service;

/**
 * @author Li
 */
@Mapper
@Service
public interface AdminUserMapper extends BaseMapper<UserEntity> {
    @Update("UPDATE sign_up SET password=#{password}, email = #{email} WHERE id=#{id}")
    void updateById(String password, String email, Integer id);
}
