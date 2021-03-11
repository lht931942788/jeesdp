package cn.org.rookie;

import cn.org.rookie.component.Response;
import cn.org.rookie.utils.JsonUtils;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@SpringBootApplication
//@EnableWorkflowConfiguration
@EnableTransactionManagement
@MapperScan("cn.org.rookie.mapper")
@RestController
public class JeesdpApplication {

    public static void main(String[] args) {
        SpringApplication.run(JeesdpApplication.class, args);
    }

    @RequestMapping("demo")
    public Response demo(Demo record) {
        String jsonString = JsonUtils.toJsonString(record);
        System.out.println(jsonString);
        return Response.success(record);
    }
}

class Demo {
    Date demo;

    public Date getDemo() {
        return demo;
    }

    public void setDemo(Date demo) {
        this.demo = demo;
    }
}
