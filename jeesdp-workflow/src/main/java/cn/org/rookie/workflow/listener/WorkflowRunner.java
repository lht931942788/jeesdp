package cn.org.rookie.workflow.listener;

import cn.org.rookie.workflow.ProcessEngine;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

//@Component
public class WorkflowRunner implements ApplicationRunner {

    private final ProcessEngine processEngine;

    public WorkflowRunner(ProcessEngine processEngine) {
        this.processEngine = processEngine;
    }

    @Override
    public void run(ApplicationArguments args) {
        processEngine.init();
    }

}
