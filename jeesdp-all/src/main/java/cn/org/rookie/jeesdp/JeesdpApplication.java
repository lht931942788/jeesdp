package cn.org.rookie.jeesdp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
//@EnableWorkflowConfiguration
@EnableTransactionManagement
public class JeesdpApplication {

    public static void main(String[] args) {
        SpringApplication.run(JeesdpApplication.class, args);
    }
}
