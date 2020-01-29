package com.demo.bbs.scevice;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;

public interface UploadService {

    void uploadAvatar(MultipartFile file, HttpSession session,String title);
}
