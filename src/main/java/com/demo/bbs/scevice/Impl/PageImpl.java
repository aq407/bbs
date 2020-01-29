package com.demo.bbs.scevice.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.demo.bbs.entity.PagesEntity;
import com.demo.bbs.entity.PublicEntity;
import com.demo.bbs.mapper.IndexMapper;
import com.demo.bbs.scevice.PageService;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;

@Service
@Mapper
public class PageImpl implements PageService {
    @Autowired
    private IndexMapper indexMapper;
    List<PublicEntity> lists;

    PagesEntity pages = new PagesEntity();

    @Override
    public void paging(Integer pagel, Model model, Model mode2) {

        List<PublicEntity> enttityList = indexMapper.selectList(null);//查询全部记录


        //分页
        QueryWrapper<PublicEntity> queryWrapper = new QueryWrapper<PublicEntity>();

        queryWrapper.ge("id", 1);//查询条件，id > 1

        Page<PublicEntity> page = new Page<PublicEntity>(pagel, pages.getPageSize());//1页5条数据

        IPage<PublicEntity> iPage = indexMapper.selectPage(page, queryWrapper);

        pages.setPageTotal((int) iPage.getTotal());//记录数
        pages.setAPage((int) iPage.getPages());//总页数
        //System.out.println("总页数" + iPage.getPages());
        //System.out.println("中记录数" + iPage.getTotal());
        lists = iPage.getRecords();//每页的数据

        mode2.addAttribute("p", pages);//页数传回给前端
        model.addAttribute("ls", lists);//每页数据传回给前端

    }
}
