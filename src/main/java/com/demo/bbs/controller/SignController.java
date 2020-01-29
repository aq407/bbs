package com.demo.bbs.controller;

import com.demo.bbs.entity.UserEntity;
import com.demo.bbs.mapper.SignMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.HashMap;

@Controller
public class SignController {

    @Autowired
    private SignMapper signMapper;

    UserEntity user = new UserEntity();


    @GetMapping("/login")
    public String login(){

        return "sign";
    }

    @PostMapping("/sign_up")
    public String sign(@RequestParam("username") String username,
                       @RequestParam("password") String password,
                       @RequestParam("password2") String password2,
                       @RequestParam("email") String email){

        if (password.equals(password2)){
            user.setUsername(username);
            user.setPassword(password);
            user.setSigntime(LocalDateTime.now());
            user.setEmail(email);
            signMapper.InserUser(user);
        }

        return "login";
    }

    @PostMapping("/sign_in")
    public String sign_in(@RequestParam("username") String username,
                          @RequestParam("password") String password,
                          HttpSession session){

        HashMap hashMap = signMapper.findAll(username);

        System.out.println(hashMap.get("username"));
        if (hashMap.get("password").equals(password)){
            session.setAttribute("username",username);
            session.setAttribute("avatar",hashMap.get("avatar"));
            session.setAttribute("userId",hashMap.get("id"));

            System.out.println(hashMap.get("avatar"));
            System.out.println(hashMap.get("id"));
            System.out.println("登录成功");
            return "redirect:/";
        }else {
            return "redirect:login";
        }

    }
}
