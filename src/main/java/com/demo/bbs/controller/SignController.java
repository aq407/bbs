package com.demo.bbs.controller;

import com.demo.bbs.entity.UserEntity;
import com.demo.bbs.mapper.SignMapper;
import com.demo.bbs.mapper.UserMapper;
import com.demo.bbs.scevice.SignEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.HashMap;

/**
 * 用户注册登录
 *
 * @author Li
 */
@Controller
public class SignController {

    /**
     * 注册mapper
     */
    @Autowired
    private SignMapper signMapper;

    @Autowired
    private UserMapper userMapper;

    UserEntity user = new UserEntity();

    @Autowired
    private SignEmail signEmail;

    @GetMapping("/login")
    public String login() {

        return "sign";
    }

    @PostMapping("/sign_up")
    @ResponseBody
    public HashMap sign(String r_user_name,
                       String r_password,
                       String r_password2,
                       String r_email) {

        HashMap<Object, Object> result = new HashMap<>(2);
        String signName = r_user_name.trim();
        String username = userMapper.userName(signName);
        if (username != null) {
            System.out.println("该用户名已经注册");
            result.put("result", "repeat");
            return result;

        }

        if (r_password.equals(r_password2) && !signName.isEmpty()) {


            user.setUsername(r_user_name);
            user.setPassword(r_password);
            user.setSigntime(LocalDateTime.now());
            user.setEmail(r_email);
            user.setAvatar("/img/default.jpg");
            signMapper.inserUser(user);

            result.put("result", "success");

           String setSubject = "注册成功";
           String setText = "欢迎您在" + LocalDateTime.now() + "注册了本站";
           String setTo = user.getEmail();

           signEmail.sendEmail(setSubject,setText,setTo);

            return result;
        } else {

            result.put("result", "fail");
            return result;
        }


    }

    /**
     * 登录
     */
    @PostMapping("/sign_in")
    @ResponseBody
    public HashMap sign_in(String username, String password,
                           HttpSession session, HttpServletRequest request) {
        System.out.println(username);
        HashMap<Object, Object> result = new HashMap(2);
        HashMap hashMap = signMapper.findAll(username);
        /**判断传来的账号和密码是否为空*/
        if (username.isEmpty() || password.isEmpty() || hashMap == null) {
            result.put("result", "fail");
            return result;
        }
        System.out.println(hashMap.get("username"));
        /**根据用户名查询密码，如果匹配则将信息写入session*/
        if (hashMap.get("password").equals(password)) {
            session.removeAttribute("username");
            session.setAttribute("username", username);
            session.setAttribute("avatar", hashMap.get("avatar"));
            session.setAttribute("userId", hashMap.get("id"));
            System.out.println(hashMap.get("avatar"));
            System.out.println(hashMap.get("id"));
            System.out.println("登录成功");
            result.put("result", "succeed");
            return result;
        } else {
            result.put("result", "fail");
            return result;
        }

    }
}
