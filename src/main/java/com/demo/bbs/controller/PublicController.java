package com.demo.bbs.controller;

import com.demo.bbs.scevice.Impl.PublicServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
/**
 * 发表帖子
 * @author Li
 * Data 2020/1
 */
@Controller
public class PublicController {
    @Autowired
    private PublicServiceImpl publicService;

    @GetMapping("/public")
    public String release() {
        return "public";
    }

    @PostMapping("/userPublic")
    public String sumbit(@RequestParam("test-editormd-markdown-doc") String editormd,
                         @RequestParam("title") String title,
                         @RequestParam("synopsis") String synopsis,
                         @RequestParam("covers") MultipartFile covers,
                         @RequestParam("tag") String tag,
                         HttpSession session) {


        publicService.PublicSave(editormd,title,synopsis,covers,tag,session);

        return "redirect:/";
    }


}
