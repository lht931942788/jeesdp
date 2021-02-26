package cn.org.rookie.jeesdp.workflow.definitions;

import cn.org.rookie.jeesdp.workflow.entity.Task;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.Map;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class SequenceFlow extends AbstractFlow {

    @Override
    public boolean exec(Task task, Map<String, Object> params) {
        AbstractModel node = task.getProcess().getNode(getTargetRef());
        processService.updateTask(task.getId(), node, (int) params.get("status"));
        if (node instanceof UserTask) {
            UserTask userTask = (UserTask) node;
            processService.insertTaskUser(userTask.getUserIds().split(","), task.getId());
        } else {
            return node.exec(task, params);
        }
        return false;
    }
}
