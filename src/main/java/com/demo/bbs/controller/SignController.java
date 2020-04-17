package com.demo.bbs.controller;

import com.demo.bbs.entity.UserEntity;
import com.demo.bbs.mapper.SignMapper;
import com.demo.bbs.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

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


    @GetMapping("/login")
    public String login() {

        return "sign";
    }

    @PostMapping("/sign_up")
    @ResponseBody
    public String sign(String name,
                       String pwd,
                       String pwd2,
                       String email) {

        HashMap<String, String> result = new HashMap<>(2);
        System.out.println(name + pwd);
        String username = userMapper.userNmae(name);
        System.out.println("---" + username);
        if (username != null) {
            System.out.println("该用户名已经注册");
            return result.put("result", "repeat");

        }
        if (pwd.equals(pwd2) && !name.isEmpty()) {
            user.setUsername(name);
            user.setPassword(pwd);
            user.setSigntime(LocalDateTime.now());
            user.setEmail(email);
            signMapper.inserUser(user);
            System.out.println("格式错误"+ name.isEmpty());
            return result.put("success", "success");
        } else {
            System.out.println("---fail");
            return result.put("result", "fail");
        }


    }

    /**
     * 登录
     */
    @PostMapping("/sign_in")
    @ResponseBody
    public HashMap sign_in(String username, String password,
                           HttpSession session) {
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
