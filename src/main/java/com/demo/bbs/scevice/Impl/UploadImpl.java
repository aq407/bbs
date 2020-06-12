package com.demo.bbs.scevice.Impl;

import com.demo.bbs.scevice.UploadService;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


@Service("UploadService")
//封面上传
public class UploadImpl implements UploadService {
    @Override
    public void uploadAvatar(MultipartFile file, HttpSession session, String title) {

        session.getAttribute("username");

//选择了文件，开始上传操作
        try {
            System.out.println(file);
            File destFile = new File(ResourceUtils.getURL("classpath:").getPath());//构建上传目标路径，找到了项目的target的classes目录

            if (!destFile.exists()) {//如果目录不存在则新建
                destFile = new File("");
            }
            System.out.println("文件的绝对路径file path:" + destFile.getAbsolutePath());//输出目标文件的绝对路径

            String paht = (String) session.getAttribute("username");
            File upload = new File(destFile.getAbsolutePath(), "src/main/resources/static/image/" + paht);

            if (!upload.exists()) {
                upload.mkdirs();//若目标文件夹不存在，则创建
            }
            System.out.println("完整的上传路径：" + upload.getAbsolutePath() + "/" + file);

            String fileName = file.getOriginalFilename();//获取上传图片的原本名字

            String fileTyle = fileName.substring(fileName.lastIndexOf(".") + 1);//获取图片后缀名

            String newFileName = title + "_" + "covers" + "." + fileTyle; //图片名字拼接
            //根据srcFile大小，准备一个字节数组
            byte[] bytes = file.getBytes();
            Path path = Paths.get(upload.getAbsolutePath() + "/" + newFileName);

            Files.write(path, bytes);//** 开始将源文件写入目标地址
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
