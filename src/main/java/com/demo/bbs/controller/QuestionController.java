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

/**
 * 回帖
 *
 * @author Li
 */
@Controller
public class QuestionController {

    @Autowired
    private PublicMapper publicMapper;

    @Autowired
    private QuestionMapper questionMapper;

    ReplyEntity replyEntity = new ReplyEntity();

    /**
     * 帖子详情页面
     */
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
        Map<String, String> map = new HashMap<>(2);
        Object loginName = session.getAttribute("userId");

        System.out.println(session.getAttributeNames());
        if (loginName == null) {

            map.put("result", "fail");

            return map;

        } else {

            /**插入回复者信息*/
            replyEntity.setPublicTitle(title);
            replyEntity.setReplyContent(text1);
            replyEntity.setReplyTime(LocalDateTime.now());
            replyEntity.setReplyUserid((Long) session.getAttribute("userId"));
            replyEntity.setReplyPublicid(id.longValue());


            /**根据回复者id查询用户名和头像*/
            HashMap h = questionMapper.findUser(replyEntity.getReplyUserid());


            /**插入回复者的用户名*/
            replyEntity.setUsername((String) h.get("username"));
            /**插入回复者头像*/
            replyEntity.setAvatar((String) h.get("avatar"));

            questionMapper.save(replyEntity);
//        return "redirect:/question/" + id;

            System.out.println("text1 : " + text1);

            map.put("result", "success");

            return map;
        }
    }
}
