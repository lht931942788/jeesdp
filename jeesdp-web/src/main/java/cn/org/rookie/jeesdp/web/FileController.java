package cn.org.rookie.jeesdp.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

public class FileController {

    @RequestMapping("/upload")
    public String upload(MultipartFile file) {
        String fileName = file.getOriginalFilename();
        return "上传失败！";
    }

}
