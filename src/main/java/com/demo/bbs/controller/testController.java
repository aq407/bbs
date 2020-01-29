package com.demo.bbs.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;


@Controller
public class testController {

    @GetMapping("/test")
    public String test() {

        return "test";
    }

    @PostMapping("/ajax")
    @ResponseBody
    public Map<String,String> ajax(HttpServletRequest request, HttpServletResponse response){

        Map<String,String> map = new HashMap<String,String>();

        String name = request.getParameter("text");
        String pwd = request.getParameter("pwd");

        System.out.println(name + " " + pwd);

        map.put("result","success");

        return map;
    }


}
