package cn.org.rookie.jeesdp;

import cn.org.rookie.jeesdp.component.Response;
import cn.org.rookie.jeesdp.utils.DateUtils;
import cn.org.rookie.jeesdp.utils.JsonUtils;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
//@EnableWorkflowConfiguration
@EnableTransactionManagement
@MapperScan("cn.org.rookie.mapper")
@RestController
public class JeesdpApplication {

    public static void main(String[] args) {
        SpringApplication.run(JeesdpApplication.class, args);
        System.out.println(DateUtils.now());
    }

    @RequestMapping("demo")
    public Response demo(@RequestBody Demo record, String bb) {
        String jsonString = JsonUtils.toJsonString(record);
        System.out.println(jsonString);
        return Response.success(record);
    }
}

class Demo {
    String demo;

    public String getDemo() {
        return demo;
    }

    public void setDemo(String demo) {
        this.demo = demo;
    }
}
