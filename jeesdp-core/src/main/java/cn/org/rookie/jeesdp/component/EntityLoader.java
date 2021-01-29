package cn.org.rookie.jeesdp.component;

import cn.org.rookie.jeesdp.utils.ScriptUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.io.File;

@Component
public class EntityLoader implements ApplicationRunner {

    public final Logger log = LoggerFactory.getLogger(EntityLoader.class);

    @Override
    public void run(ApplicationArguments args) {
        File src = new File(System.getProperty("user.dir") + File.separator + "script" + File.separator + "src");
        File[] files = src.listFiles();
        if (files != null) {
            for (File file : files) {
                Class<?> entityClass = ScriptUtils.parse(file);
                if (entityClass != null) {
                    Entity entity = new Entity(entityClass);
                    EntityContext.put(entityClass.getSimpleName(), entity);
                    if (log.isDebugEnabled()) {
                        log.debug("Load " + entityClass.getName() + " successed");
                    }
                }
            }
        }
    }
}
