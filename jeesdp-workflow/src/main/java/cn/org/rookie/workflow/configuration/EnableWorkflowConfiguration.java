package cn.org.rookie.workflow.configuration;

import cn.org.rookie.workflow.ProcessEngine;
import cn.org.rookie.workflow.listener.WorkflowRunner;
import cn.org.rookie.workflow.dao.ProcessDao;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Import({ProcessEngine.class, WorkflowRunner.class, WorkflowProperties.class, ProcessDao.class})
public @interface EnableWorkflowConfiguration {
}
