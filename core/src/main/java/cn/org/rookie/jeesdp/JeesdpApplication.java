package cn.org.rookie.jeesdp;

import cn.org.rookie.jeesdp.component.Response;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.Map;

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
    public Response demo(@RequestParam Map<String, Object> record) {
        return Response.success(record).put("record", record);
    }
}

@Repository
class DemoRepository {

}

class Demo {
    String demo;
    Date date;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
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
