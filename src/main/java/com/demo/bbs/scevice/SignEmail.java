package com.demo.bbs.scevice;

/**
 *
 * @author lI
 *
 */
public interface SignEmail {

    /**
     * 发送邮件
     * @param setSubject 邮件标题
     * @param setText 右键正文
     * @param setTo 接收者邮箱地址
     */
    boolean sendEmail(String setSubject,String setText,String setTo);
}
