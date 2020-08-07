package cn.org.rookie.admin.controller;

import cn.org.rookie.admin.service.WorkflowService;
import cn.org.rookie.workflow.definitions.Process;
import org.springframework.web.bind.annotation.RequestMapping;

//@RestController
@RequestMapping("workflow")
public class WorkflowController {

    private final WorkflowService workflowService;

    public WorkflowController(WorkflowService workflowService) {
        this.workflowService = workflowService;
    }

    @RequestMapping("getContent")
    public String getContent(String id) {
        return null;
    }

    @RequestMapping("getProcessById")
    public Process getProcessById(String id) {
        return workflowService.selectProcessById(id);
    }

    @RequestMapping("saveProcess")
    public void saveProcess(Process process) {
        workflowService.insertProcess(process);
    }

    @RequestMapping("updateProcess")
    public void updateProcess(Process process) {
        workflowService.updateProcess(process);
    }

    @RequestMapping("updateProcessContent")
    public void updateProcessContent(String content, String id) {
        workflowService.updateProcessContent(content, id);
    }

}
