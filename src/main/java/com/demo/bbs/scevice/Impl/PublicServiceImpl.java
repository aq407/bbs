package com.demo.bbs.scevice.Impl;

import com.demo.bbs.entity.PublicEntity;
import com.demo.bbs.mapper.PublicMapper;
import com.demo.bbs.scevice.PublicService;
import com.demo.bbs.scevice.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;

@Service
public class PublicServiceImpl implements PublicService {

    @Autowired
    private PublicEntity publicEntity;

    @Autowired
    private UploadService uploadService;

    @Autowired
    private PublicMapper publicMapper;

    @Override
    public void PublicSave(String editormd, String title, String synopsis, MultipartFile covers, String tag,HttpSession session) {


        String fileName = covers.getOriginalFilename();
        String fileTyle = fileName.substring(fileName.lastIndexOf(".") + 1);

        publicEntity.setTitle(title);
        publicEntity.setSynopsis(synopsis);
        publicEntity.setContent(editormd);
        publicEntity.setCovers("/image/" + (String) session.getAttribute("username") + "/" + title + "_" + "covers" + "." + fileTyle);
        uploadService.uploadAvatar(covers, session, title);
        publicEntity.setTag(tag);
        publicEntity.setTime(LocalDateTime.now());
        publicEntity.setUser((String) session.getAttribute("username"));
        publicMapper.save(publicEntity);
    }
}
