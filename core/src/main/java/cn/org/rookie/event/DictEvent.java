package cn.org.rookie.event;

import cn.org.rookie.context.DictContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextStartedEvent;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

/**
 * 项目启动后加载所有字典
 *
 * @author LIHAITAO
 */

@Component
public class DictEvent implements ApplicationListener<ContextStartedEvent> {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void onApplicationEvent(ContextStartedEvent event) {
        DictContext.init(jdbcTemplate.queryForList("select * from t_sys_dict"));


    }

}
