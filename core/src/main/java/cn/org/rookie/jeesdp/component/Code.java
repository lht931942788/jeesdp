package cn.org.rookie.jeesdp.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextStartedEvent;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

/**
 * @author LIHAITAO
 */

@Component
public class Code implements ApplicationListener<ContextStartedEvent> {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void onApplicationEvent(ContextStartedEvent event) {

    }
}
