package cn.org.rookie.jeesdp.workflow.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Model {

    private String id;
    private String name;
    private long status;
    private Date createTime;
    private Date updateTime;
    private Resource resource;

}
