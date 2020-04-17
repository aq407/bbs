package com.demo.bbs.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.demo.bbs.entity.PublicEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Service;

/**
 * @author Administrator
 */
@Mapper
@Service
public interface PublicMapper extends BaseMapper<PublicEntity> {


    void save(PublicEntity publicEntity);

    @Select("select * from public where id = #{id}")
    PublicEntity list(Integer id);
}
