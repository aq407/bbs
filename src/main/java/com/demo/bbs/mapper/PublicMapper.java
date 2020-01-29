package com.demo.bbs.mapper;

import com.demo.bbs.entity.PublicEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Service;

import java.util.List;

@Mapper
@Service
public interface PublicMapper {

    void save(PublicEntity publicEntity);

    @Select("select * from public where id = #{id}")
    PublicEntity list(Integer id);
}
