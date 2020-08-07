package cn.org.rookie.workflow.entity;

import cn.org.rookie.workflow.definitions.AbstractModel;
import cn.org.rookie.workflow.definitions.Process;
import lombok.Data;
import lombok.ToString;

import java.util.Date;

@Data
@ToString
public class Task {
    private String id;
    private String processId;
    private String currentNodeId;
    private int status;
    private Date startTime;
    private Date endTime;
    private User creator;
    private Process process;
    private AbstractModel currentNode;
}
