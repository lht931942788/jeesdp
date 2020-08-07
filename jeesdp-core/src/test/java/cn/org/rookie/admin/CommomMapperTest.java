package cn.org.rookie.admin;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.io.IOException;

@SpringBootTest
public class CommomMapperTest {
    @Autowired
    NamedParameterJdbcTemplate jdbcTemplate;

    @Test
    public void contextLoads() {
        PathMatchingResourcePatternResolver patternResolver = new PathMatchingResourcePatternResolver();
        try {
            Resource[] resources = patternResolver.getResources("classpath*:cn/org/rookie/**/mapper");
            System.out.println(resources);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
