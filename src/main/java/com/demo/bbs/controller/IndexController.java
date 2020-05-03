package com.demo.bbs.controller;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.demo.bbs.entity.PageEntity;
import com.demo.bbs.entity.PagesEntity;
import com.demo.bbs.entity.PublicEntity;
import com.demo.bbs.entity.UserEntity;
import com.demo.bbs.scevice.PageService;
import com.demo.bbs.scevice.PagesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.TreeSet;

/**
 * 主页控制器
 *
 * @author Li
 */
@Controller
public class IndexController {

    /**
     * 分页服务
     */

    @Autowired
    private PagesService pagesService;

    @GetMapping("/")
    public String index() {

        return "index";
    }

    @PostMapping("/index")
    @ResponseBody
    public String indexPost(Long pagenum) {

        if (pagenum == null) {
            pagenum = 1L;
        }


        IPage<PublicEntity> ipage = pagesService.publicPages(pagenum, 10L);
        List result = ipage.getRecords();


        PagesEntity pagesEntity = new PagesEntity();
        pagesEntity.setPublicEntities(result);
        pagesEntity.setTotal(ipage.getTotal());
        String publics = JSON.toJSONString(pagesEntity);
        return publics;
    }

}
