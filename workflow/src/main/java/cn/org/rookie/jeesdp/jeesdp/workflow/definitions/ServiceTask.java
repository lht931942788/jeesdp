package cn.org.rookie.jeesdp.jeesdp.workflow.definitions;

import cn.org.rookie.jeesdp.jeesdp.workflow.entity.Task;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.Map;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class ServiceTask extends AbstractTask {
    @Override
    public boolean exec(Task task, Map<String, Object> params) {
        AbstractModel node = getNextNode(task, params);
        processService.insertLog(task.getId(), this, "系统自动执行任务：" + getName(), 2, false);
        return node.exec(task, params);
    }
}
