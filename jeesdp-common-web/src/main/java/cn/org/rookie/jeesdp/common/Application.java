package cn.org.rookie.jeesdp.common;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.*;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Enumeration;

/**
 * @author LIHAITAO
 */

@SpringBootApplication
@RestController
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @RequestMapping("upload")
    public String upload(MultipartFile[] files, HttpServletRequest request) {
        Enumeration<String> names = request.getHeaderNames();
        while (names.hasMoreElements()) {
            System.out.println(names.nextElement());
        }
        request.getParameterMap().forEach((name, value) -> {
            System.out.println(name + ":" + Arrays.toString(value));
        });
        Arrays.stream(files).forEach(multipartFile -> {
            System.out.println(multipartFile.getOriginalFilename());
        });
        return "上传失败！";
    }

    public ResponseEntity<byte[]> donwload() {
        File file = new File("");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentDisposition(ContentDisposition.attachment().filename(file.getName(), StandardCharsets.ISO_8859_1).build());
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        try {
            return new ResponseEntity<>(FileCopyUtils.copyToByteArray(file), headers, HttpStatus.CREATED);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
