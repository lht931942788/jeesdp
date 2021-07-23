package cn.org.rookie.jeesdp.core.listener;

import cn.org.rookie.jeesdp.core.JeesdpContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextStartedEvent;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 项目启动后加载所有字典
 *
 * @author LIHAITAO
 */

@Component
public class DictListener implements ApplicationListener<ContextStartedEvent> {

    private final JdbcTemplate jdbcTemplate;

    public DictListener(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void onApplicationEvent(ContextStartedEvent event) {

        List<Map<String, Object>> maps = jdbcTemplate.queryForList("select * from t_sys_dict");
        Iterator<Map<String, Object>> iterator = maps.iterator();
        while (iterator.hasNext()) {
            Map<String, Object> row = iterator.next();
            Map<String, Object> dict;
            Object parent = row.get("parent");
            String code = row.get("code").toString();
            if ("0".equals(parent)) {
                dict = new LinkedHashMap<>();
                JeesdpContext.putDict(code, dict);
            } else {
                dict = JeesdpContext.getDict(parent.toString());
                dict.put(code, dict.get("name"));
            }
            iterator.remove();
        }
    }

}
