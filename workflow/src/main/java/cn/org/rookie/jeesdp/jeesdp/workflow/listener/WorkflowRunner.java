package cn.org.rookie.jeesdp.jeesdp.workflow.listener;

import cn.org.rookie.jeesdp.jeesdp.workflow.ProcessEngine;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;

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
