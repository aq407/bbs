package com.demo.bbs.mapper;

import com.demo.bbs.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Service;

import java.util.HashMap;


@Mapper
@Service

public interface SignMapper {

     void InserUser(UserEntity user);

     HashMap<String,String> findAll(String username);

}
