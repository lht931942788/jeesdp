package cn.org.rookie.jeesdp.core;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SingleColumnRowMapper;

import java.sql.Date;
import java.util.UUID;

@SpringBootTest
@SpringBootApplication
public class ApplicationTest {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Test
    public void init() {
        System.out.println(UUID.randomUUID());
        Long object = jdbcTemplate.queryForObject("select uuid_short() from dual", new SingleColumnRowMapper<>(Long.class));
        System.out.println(object);
        jdbcTemplate.update("insert into demo (date, timestamp) VALUES (?, ?)", new Date(System.currentTimeMillis()), UUID.randomUUID().toString());
        //System.out.println(jdbcTemplate.query("select * from demo", new BeanPropertyRowMapper<>(Demo.class)).get(5).timestamp.toString());
    }
}
