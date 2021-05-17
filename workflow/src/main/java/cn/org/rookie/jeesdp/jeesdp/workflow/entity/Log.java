package cn.org.rookie.jeesdp.jeesdp.workflow.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Log {

    private String id;
    private String taskId;
    private String nodeId;
    private String nodeName;
    private User operator;
    private String operateName;
    private String remark;
    private Integer status;
    private Date createTime;

}
