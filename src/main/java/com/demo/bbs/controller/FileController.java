package com.demo.bbs.controller;


import com.demo.bbs.dto.FileDTO;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * 文件上传
 * @author Li
 */
@Controller
public class FileController {
    @RequestMapping("/files/upload")
    @ResponseBody
    public FileDTO upload(@RequestParam(value = "editormd-image-file", required = false) MultipartFile file, HttpSession session) {

        FileDTO fileDTO = new FileDTO();

        try {

            /**构建上传目标路径，找到了项目的target的classes目录
             * 如果目录不存在则新建*/
            File destFile = new File(ResourceUtils.getURL("classpath:").getPath());

            if (!destFile.exists()) {
                destFile = new File("");
            }

            /**输出目标文件的绝对路径*/
            System.out.println("文件的绝对路径file path:" + destFile.getAbsolutePath());

            String paht = (String) session.getAttribute("username");
            File upload = new File(destFile.getAbsolutePath(), "src/main/resources/static/user/" + paht);

            /**若目标文件夹不存在，则创建*/
            if (!upload.exists()) {
                upload.mkdirs();
            }
            System.out.println("完整的上传路径：" + upload.getAbsolutePath() + "/" + file);


            //根据srcFile大小，准备一个字节数组
            byte[] bytes = file.getBytes();

            Path path = Paths.get(upload.getAbsolutePath() + "/" + file.getOriginalFilename());
            /**开始将源文件写入目标地*/
            Files.write(path, bytes);
            fileDTO.setSuccess(1);
            fileDTO.setUrl("user/" +paht + "/" + file.getOriginalFilename());


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return fileDTO;
    }
}
