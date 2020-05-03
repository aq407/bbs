package com.demo.bbs.scevice;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.demo.bbs.entity.PublicEntity;
import com.demo.bbs.entity.UserEntity;

/**
 * @author LI*/
public interface PagesService {

    IPage<PublicEntity> publicPages(Long current, Long size);

    /**
     * 分页
     * @param current 当前页数
     * @param size 每页显示数据的数目
     *
     */
    IPage<UserEntity> userPages(Long current, Long size);

    //IPage<要分类的数据的实体类> 接口名 （当前页数，每页展示的大小）
}
