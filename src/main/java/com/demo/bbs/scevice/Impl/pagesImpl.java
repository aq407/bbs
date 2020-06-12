package com.demo.bbs.scevice.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.demo.bbs.entity.PublicEntity;
import com.demo.bbs.entity.UserEntity;
import com.demo.bbs.mapper.UserMapper;
import com.demo.bbs.mapper.testMapper;
import com.demo.bbs.scevice.PagesService;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;


/**
 * 分页
 *
 * @author Li
 */
@Mapper
@Service

public class pagesImpl implements PagesService {

    @Autowired
    private testMapper testMapper;

    @Autowired
    private UserMapper userMapper;

    @Override

    public IPage<PublicEntity> publicPages(Long current, Long size) {
        QueryWrapper<PublicEntity> queryWrapper = new QueryWrapper<PublicEntity>();
        queryWrapper.orderByDesc("time");

        Page<PublicEntity> page = new Page<>(current, size);
        IPage<PublicEntity> iPage = testMapper.selectPage(page, queryWrapper);
        return iPage;

    }

    @Override
    public IPage<UserEntity> userPages(Long current, Long size) {
        Page<UserEntity> page = new Page<>(current, size);
        IPage<UserEntity> iPage = userMapper.selectPage(page, null);

        return iPage;
    }


}
