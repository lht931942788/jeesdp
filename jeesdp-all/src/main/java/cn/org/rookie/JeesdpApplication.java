package cn.org.rookie;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
//@EnableWorkflowConfiguration
@EnableTransactionManagement
@MapperScan("cn.org.rookie.mapper")
public class JeesdpApplication {

    public static void main(String[] args) {
        SpringApplication.run(JeesdpApplication.class, args);
    }
}
