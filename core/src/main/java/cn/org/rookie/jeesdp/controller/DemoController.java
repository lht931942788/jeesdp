package cn.org.rookie.jeesdp.controller;

import cn.org.rookie.jeesdp.component.Response;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

/**
 * @author LIHAITAO
 * @date 2021-06-01 20:27
 */

@RestController
public class DemoController {

    @RequestMapping("demo")
    public Response demo(@RequestBody Demo record) {
        return Response.success(record).put("record", record);
    }
}

class Demo {
    String demo;
    LocalDateTime date;

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getDemo() {
        return demo;
    }

    public void setDemo(String demo) {
        this.demo = demo;
    }

    @Override
    public String toString() {
        return "Demo{" +
                "demo='" + demo + '\'' +
                ", date=" + date +
                '}';
    }
}
