package cn.org.rookie.jeesdp.workflow.configuration;

import cn.org.rookie.jeesdp.workflow.ProcessEngine;
import cn.org.rookie.jeesdp.workflow.dao.ProcessDao;
import cn.org.rookie.jeesdp.workflow.listener.WorkflowRunner;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Import({ProcessEngine.class, WorkflowRunner.class, WorkflowProperties.class, ProcessDao.class})
public @interface EnableWorkflowConfiguration {
}
