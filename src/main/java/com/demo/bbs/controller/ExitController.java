package com.demo.bbs.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
/**
 * 推出登录
 * @author Li
 * Data 2020/1
 */
@Controller
public class ExitController {

    @PostMapping("/exit")
    public String exits(HttpSession session){

        session.removeAttribute("username");
        session.removeAttribute("avatar");
        session.removeAttribute("userId");

        return "redirect:/";
    }


}
