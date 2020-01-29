package com.demo.bbs.controller;

import com.demo.bbs.entity.PublicEntity;
import com.demo.bbs.entity.ReplyEntity;
import com.demo.bbs.mapper.PublicMapper;
import com.demo.bbs.mapper.QuestionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller

public class QuestionController {

    @Autowired
    private PublicMapper publicMapper;

    @Autowired
    private QuestionMapper questionMapper;

    ReplyEntity replyEntity = new ReplyEntity();

    @GetMapping("/question/{id}")
    public String que(@PathVariable(name = "id") Integer id,
                      Model model,
                      Model reply) {

        PublicEntity publicEntity = publicMapper.list(id);
        model.addAttribute("c", publicEntity);

        List<ReplyEntity> lists = questionMapper.replyList(id.longValue());

        reply.addAttribute("replys", lists);


        return "question";
    }

    @PostMapping("/reply/{id}")
    @ResponseBody
    public Map<String, String> reply(@PathVariable(name = "id") Integer id,
//                      @RequestParam("test-editormd-markdown-doc") String content,

                                     HttpSession session,
                                     HttpServletRequest request,
                                     HttpServletResponse response) {
        String text1 = request.getParameter("text1");
        String title = request.getParameter("title");
        System.out.println("标题是：" + title);

        session.getAttribute("username");

        System.out.println(session.getAttribute("username"));

        replyEntity.setPublicTitle(title);//插入回复标题

        replyEntity.setReplyContent(text1);//插入回复内容

        replyEntity.setReplyTime(LocalDateTime.now());//插入回复时间

        replyEntity.setReplyUserid((Long) session.getAttribute("userId"));//插入回复者的id

        System.out.println(session.getAttribute("userId").getClass().getTypeName());

        System.out.println("回复者ID是:"+replyEntity.getReplyUserid().getClass().getTypeName());

        replyEntity.setReplyPublicid(id.longValue());//插入回复主题id

        HashMap h = questionMapper.findUser( replyEntity.getReplyUserid());//根据回复者id查询用户名和头像
        System.out.println(id);
        System.out.println(h.get("username"));
        System.out.println(h.get("avatar"));
        replyEntity.setUsername((String) h.get("username"));//插入回复者的用户名
        replyEntity.setAvatar((String) h.get("avatar"));//插入回复者头像

        questionMapper.save(replyEntity);


//        return "redirect:/question/" + id;






        System.out.println("text1 : " + text1);
        Map<String, String> map = new HashMap<String, String>();
        map.put("result","success");

        return map;
    }
}
