package cn.org.rookie.jeesdp.core;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Date;

@SpringBootTest
@SpringBootApplication
public class ApplicationTest {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Test
    public void init() {
        jdbcTemplate.update("insert into demo (date, timestamp) VALUES (?, ?)", new Date(), new Date());
        //System.out.println(jdbcTemplate.query("select * from demo", new BeanPropertyRowMapper<>(Demo.class)).get(5).timestamp.toString());
    }
}
