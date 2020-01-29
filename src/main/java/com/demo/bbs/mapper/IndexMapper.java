package com.demo.bbs.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.demo.bbs.entity.PublicEntity;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Service;

@Mapper
@Service
public interface IndexMapper extends BaseMapper<PublicEntity> {

//    @Select("select * from public")
//    List<PublicEntity> listAll();

}
