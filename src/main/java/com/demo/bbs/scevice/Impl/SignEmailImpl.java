package com.demo.bbs.scevice.Impl;

import com.demo.bbs.scevice.SignEmail;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Li
 */
@Mapper
@Service
public class SignEmailImpl implements SignEmail {

    @Autowired
    JavaMailSenderImpl mailSender;


    @Override
    public boolean sendEmail(String setSubject, String setText, String setTo) {

        final String pattern1 = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
        final Pattern pattern = Pattern.compile(pattern1);
        final Matcher mat = pattern.matcher(setTo);
        boolean tag = true;
        if (mat.find()) {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setSubject(setSubject);
            message.setText(setText);
            message.setFrom("1599407173@qq.com");
            message.setTo(setTo);
            mailSender.send(message);
            System.out.println("邮箱格式"+tag);
            return tag;
        }else {
            tag = false;
            System.out.println("邮箱格式"+tag);
            return tag;
        }

    }
}
