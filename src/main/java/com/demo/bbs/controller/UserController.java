package com.demo.bbs.controller;

import com.demo.bbs.entity.PublicEntity;
import com.demo.bbs.entity.UserEntity;
import com.demo.bbs.mapper.UserMapper;
import com.demo.bbs.mapper.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;

/**用户中心
 * @author Li
 *
 */
@Controller
public class UserController {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserService userService;

    @GetMapping("/user")
    public String user(HttpSession session,
                       Model model, Model mode2) {

        Object username = session.getAttribute("username");
        Object userId = session.getAttribute("userId");
        Long id = (Long) userId;


        List<PublicEntity> list = userMapper.post((String) username);
        UserEntity userList = userService.selectById(id);

        model.addAttribute("User", list);
        mode2.addAttribute("userId", userList);
        return "user";
    }


    @PostMapping("/delete/")
    public String delete(@RequestParam("publicId") String id,
                         HttpSession session) {



        Long l = Long.parseLong(id);

        HashMap map = userMapper.findId(l);


        Object user = map.get("user");

        Object name = session.getAttribute("username");

        if (user.equals(name)) {

            System.out.println("同一用户允许操作");
            userMapper.deletePublic(l);
        } else {
            System.out.println("该用户没有权限");
        }


        return "redirect:/user";
    }

}
