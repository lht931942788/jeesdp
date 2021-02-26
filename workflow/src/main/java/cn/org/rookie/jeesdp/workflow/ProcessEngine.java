package cn.org.rookie.jeesdp.workflow;

import cn.org.rookie.jeesdp.workflow.definitions.AbstractModel;
import cn.org.rookie.jeesdp.workflow.definitions.AbstractTask;
import cn.org.rookie.jeesdp.workflow.definitions.Process;
import cn.org.rookie.jeesdp.workflow.entity.Model;
import cn.org.rookie.jeesdp.workflow.entity.Task;
import cn.org.rookie.jeesdp.workflow.service.impl.AbstractProcessService;
import cn.org.rookie.jeesdp.workflow.utils.BpmnUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
public class ProcessEngine {

    private static final Map<String, Process> CONTEXT = new ConcurrentHashMap<>();
    @Autowired
    private AbstractProcessService processService;

    public String start(String id) {
        Process process = getProcessById(id);
        if (process != null) {
            String taskId = processService.UUID();
            Task task = new Task();
            task.setId(taskId);
            task.setProcess(process);
            task.setStartTime(new Date());
            task.setCreator(processService.getCurrentUser());
            Map<String, Object> params = new HashMap<>();
            params.put("remark", "");
            params.put("status", 2);
            process.getNode(process.getStartId()).exec(task, params);
            return taskId;
        } else {
            log.error("There is no such process. id:" + id);
        }
        return null;
    }

    //TODO 加版本，将xml保存到另一张表
    public void audit(String taskId, String remark, int status) {
        Task task = processService.selectTaskById(taskId);
        Process process = getProcessById(task.getProcess().getId());
        if (process != null) {
            Map<String, Object> params = new HashMap<>();
            params.put("remark", remark);
            params.put("status", status);
            process.getNode(task.getCurrentNode().getId()).exec(task, params);
        } else {
            log.error("There is no such process. id:" + task.getProcess().getId());
        }
    }

    public Map<String, Object> getCurrentNode(String taskId) {
        Task task = processService.selectTaskById(taskId);
        Process process = getProcessById(task.getProcess().getId());
        AbstractTask node = (AbstractTask) process.getNode(task.getCurrentNode().getId());
        Map<String, Object> current = new HashMap<>();
        current.put("node", node);
        List<String> outgoings = node.getOutgoings();
        List<AbstractModel> folws = new ArrayList<>();
        for (String id : outgoings) {
            folws.add(process.getNode(id));
        }
        current.put("flows", folws);
        return current;
    }

    public Process getProcessById(String id) {
        return CONTEXT.get(id);
    }

    public Process convert(String file) {
        return BpmnUtils.convert(new File(file));
    }

    public Process convert(Model model) {
        return BpmnUtils.convert(model);
    }

    public void add(List<Model> models) {
        log.info("Initialization process start");
        models.forEach(model -> add(convert(model)));
        log.info("Initialization process end");
    }

    public void add(Process process) {
        CONTEXT.put(process.getId(), process);
        log.info("Initialization process succeeded: [id:" + process.getId() + ", name:" + process.getName() + "]");
    }

    public void update(Model model) {
        Process process = BpmnUtils.convert(model);
        CONTEXT.put(process.getId(), process);
    }

    public void init() {
        //add(processService.selectProcesses());
    }

}
