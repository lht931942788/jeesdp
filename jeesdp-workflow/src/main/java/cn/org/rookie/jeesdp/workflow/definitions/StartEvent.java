package cn.org.rookie.jeesdp.workflow.definitions;

import cn.org.rookie.jeesdp.workflow.entity.Task;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;
import java.util.Map;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class StartEvent extends AbstractModel {
    private List<String> outgoings;

    @Override
    public boolean exec(Task task, Map<String, Object> params) {
        AbstractModel node = getNextNode(task, params);
        processService.insertTask(task);
        processService.insertLog(task.getId(), this, "流程启动", 1, true);
        return node.exec(task, params);
    }
}
