package com.demo.bbs.controller;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.demo.bbs.entity.PageEntity;
import com.demo.bbs.entity.PublicEntity;
import com.demo.bbs.entity.UserEntity;
import com.demo.bbs.mapper.AdminUserMapper;
import com.demo.bbs.mapper.PublicMapper;
import com.demo.bbs.mapper.SignMapper;
import com.demo.bbs.scevice.PagesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

/**
 * @author Li
 * Data 2020/1
 */

@Controller
public class AdminController {

    @Autowired
    /**注入Mapper，实现对User的实体类的sql操作*/
    private AdminUserMapper adminUserMapper;

    @Autowired
    /**发帖的sql操作*/
    private PublicMapper publicMapper;

    @Autowired
    private PagesService pagesService;

    @Autowired

    private SignMapper signMapper;

    @GetMapping("/admin")
    public String admin() {
        return "admin";
    }


    @PostMapping("/listUser")
    @ResponseBody
    public String user(Long pagenum) {
        /**查询sign_up表中所有用户的信息*/


        /**
         * current 当前页数
         * size 每页显示的数目
         * ipage.getTotal() 总数据数*/

        if (pagenum == null) {
            pagenum = 1L;
        }
        IPage<UserEntity> ipage = pagesService.userPages(pagenum, 5L);

        /**获取到查询的数据*/
        List result = ipage.getRecords();
        PageEntity pageEntity = new PageEntity();
        //Collections.reverse(result);倒叙排序


        pageEntity.setTotal(ipage.getTotal());
        pageEntity.setUserEntity(result);

        String test = JSON.toJSONString(pageEntity);

        return test;

    }

    /**
     * 删除用户
     */
    @PostMapping("/deleteUser")
    public String deleteId(@RequestParam("userId") Integer userId) {

        adminUserMapper.deleteById(userId);

        return "redirect:/admin";

    }


    @PostMapping("/listPost")
    @ResponseBody
    public List post() {
        /**查询所有的帖子*/
        List<PublicEntity> listPublic = publicMapper.selectList(null);
        return listPublic;
    }

    @PostMapping("/deletePost")
    public String delete(@RequestParam("PostId") Long id, HttpServletResponse response, HttpServletRequest request) throws ServletException, IOException {

        /**根据id来删除帖子删除*/
        publicMapper.deleteById(id);
        return "redirect:/admin#tap2";


    }

    @PostMapping("/uploadUser")
    public String uploadUser(@RequestParam("userId") String userId,
                             @RequestParam("password") String password,
                             @RequestParam("userEmail") String userEmail) {

        Integer user = Integer.parseInt(userId);

        UserEntity users = adminUserMapper.selectById(user);

        if (password.isEmpty()) {
            password = users.getPassword();
        }

        if (userEmail.isEmpty()) {
            userEmail = users.getEmail();
        }

        if (password.length() == 0 && userEmail.length() == 0) {
            userEmail = users.getEmail();
            password = users.getPassword();
        }

        adminUserMapper.updateById(password, userEmail, user);

        return "redirect:/admin";
    }

}
