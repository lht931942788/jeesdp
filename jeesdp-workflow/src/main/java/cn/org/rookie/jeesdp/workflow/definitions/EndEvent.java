package cn.org.rookie.jeesdp.workflow.definitions;

import cn.org.rookie.jeesdp.workflow.entity.Task;

import java.util.Date;
import java.util.Map;

public class EndEvent extends AbstractModel {
    @Override
    public boolean exec(Task task, Map<String, Object> params) {
        processService.updateTask(task.getId(), 6, new Date());
        processService.deleteTaskUser(task.getId());
        processService.insertLog(task.getId(), this, "流程结束", 6, false);
        return false;
    }
}
