package com.demo.bbs.controller;

import com.demo.bbs.entity.PublicEntity;
import com.demo.bbs.mapper.PublicMapper;
import com.demo.bbs.scevice.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;

/**
 * 发表帖子
 *
 * @author Li
 * Data 2020/1
 */
@Controller
public class PublicController {
    /**
     * 文件上传服务
     */
    @Autowired
    private UploadService uploadService;

    @Autowired
    private PublicMapper publicMapper;

    PublicEntity publicEntity = new PublicEntity();

    @GetMapping("/public")
    public String release() {
        return "public";
    }

    @PostMapping("/public")
    public String sumbit(@RequestParam("test-editormd-markdown-doc") String editormd,
                         @RequestParam("title") String title,
                         @RequestParam("synopsis") String synopsis,
                         @RequestParam("covers") MultipartFile covers,
                         @RequestParam("tag") String tag,
                         HttpSession session) {


        /**获取封面的名字和上传文件的后缀*/
        String fileName = covers.getOriginalFilename();
        String fileTyle = fileName.substring(fileName.lastIndexOf(".") + 1);

        System.out.println(tag);

        /**
         * 将信息插入数据库
         */
        publicEntity.setTitle(title);
        publicEntity.setSynopsis(synopsis);
        publicEntity.setContent(editormd);
        publicEntity.setCovers("/user/" + (String) session.getAttribute("username") + "/" + title + "_" + "covers" + "." + fileTyle);
        uploadService.uploadAvatar(covers, session, title);
        publicEntity.setTag(tag);
        publicEntity.setTime(LocalDateTime.now());
        publicEntity.setUser((String) session.getAttribute("username"));

        publicMapper.save(publicEntity);
        System.out.println(editormd);
        return "public";
    }


}
