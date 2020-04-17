package com.demo.bbs.scevice;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
/**
 * 文件上传接口
 * @author Li
 * */
public interface UploadService {

    /**
     * 封面上传
     * @param file 上传的文件
     * @param session 登录信息
     * @param title 发表帖子的标题
     */
    void uploadAvatar(MultipartFile file, HttpSession session,String title);
}
