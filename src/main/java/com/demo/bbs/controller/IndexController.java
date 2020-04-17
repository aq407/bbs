package com.demo.bbs.controller;

import com.demo.bbs.scevice.PageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 主页控制器
 * @author Li*/
@Controller
public class IndexController {

    /**分页服务*/
    @Autowired
    private PageService pageService;


    @RequestMapping("/")
    public String index(Model model,
                        @RequestParam(name = "pagel",defaultValue = "1") Integer pagel,
                        Model mode2) {

/*
        List<PublicEntity> list = indexMapper.listAll();
        model.addAttribute("ls", list);
*/


        pageService.paging(pagel,model,mode2);

        return "index";
    }

}
