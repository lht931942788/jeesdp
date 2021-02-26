package cn.org.rookie.jeesdp.workflow.entity;

import lombok.Data;
import lombok.ToString;


@Data
@ToString
public class TaskUser {

    private String id;
    private String taskId;
    private String userId;
    private int status;

    public TaskUser() {
    }

    public TaskUser(String id, String taskId, String userId) {
        this.id = id;
        this.taskId = taskId;
        this.userId = userId;
    }

    public TaskUser(String taskId, String userId, int status) {
        this.taskId = taskId;
        this.userId = userId;
        this.status = status;
    }

    public TaskUser(String id, String taskId, String userId, int status) {
        this.id = id;
        this.taskId = taskId;
        this.userId = userId;
        this.status = status;
    }


}
