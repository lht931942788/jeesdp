package cn.org.rookie.workflow.definitions;

import cn.org.rookie.workflow.entity.Task;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.Map;


@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class UserTask extends AbstractTask {
    private String userIds;
    private String type;

    @Override
    public boolean exec(Task task, Map<String, Object> params) {

        if (type == null) {
            processService.insertLog(task.getId(), this, "流程通过", 2, false);
            AbstractModel node = getNextNode(task, params);
            return node.exec(task, params);
        }
        return false;
    }
}
