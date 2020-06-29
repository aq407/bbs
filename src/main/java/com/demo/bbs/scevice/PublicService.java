package com.demo.bbs.scevice;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;

public interface PublicService {

    void PublicSave(String editormd, String title, String synopsis, MultipartFile covers, String tag, HttpSession session);
}
