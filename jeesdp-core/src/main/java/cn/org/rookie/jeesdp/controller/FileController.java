package cn.org.rookie.jeesdp.controller;

import cn.org.rookie.jeesdp.core.component.Response;
import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Objects;

/**
 * @author LIHAITAO
 * @date 2021-07-07 22:36
 */

@RestController
@RequestMapping("file")
public class FileController {

    @RequestMapping("upload")
    public Response upload(@RequestParam("file") MultipartFile[] files) {
        for (MultipartFile file : files) {
            String fileName = file.getOriginalFilename();
        }
        return Response.success().put("id", "");
    }

    @RequestMapping("download")
    public void download(HttpServletResponse response) {
        FileInputStream fis = null;
        ServletOutputStream sos = null;
        try {
            String fileName = "";
            response.setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_OCTET_STREAM_VALUE);
            response.setHeader(HttpHeaders.CONTENT_DISPOSITION, String.format("attachment; filename=%s", fileName));
            fis = new FileInputStream("");
            sos = response.getOutputStream();
            IOUtils.copy(fis, sos);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                Objects.requireNonNull(fis).close();
                Objects.requireNonNull(sos).close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
