package cn.org.rookie.jeesdp;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
//@EnableWorkflowConfiguration
@EnableTransactionManagement
@MapperScan("cn.org.rookie.mapper")
@RestController
public class JeesdpApplication {

    public static void main(String[] args) {
        SpringApplication.run(JeesdpApplication.class, args);
    }


}

