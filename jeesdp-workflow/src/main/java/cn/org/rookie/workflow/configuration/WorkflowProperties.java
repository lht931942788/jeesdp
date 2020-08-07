package cn.org.rookie.workflow.configuration;

import cn.org.rookie.workflow.service.ProcessService;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "jeesdp.workflow")
public class WorkflowProperties {

    private Class<? extends ProcessService> processService;

    public Class<? extends ProcessService> getProcessService() {
        return processService;
    }

    public void setProcessService(Class<? extends ProcessService> processService) {
        this.processService = processService;
    }
}
