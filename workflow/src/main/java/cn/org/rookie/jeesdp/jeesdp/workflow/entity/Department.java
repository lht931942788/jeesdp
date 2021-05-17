package cn.org.rookie.jeesdp.jeesdp.workflow.entity;

import lombok.Data;

import java.util.List;

@Data
public class Department {
    private String id;
    private String name;
    private List<User> users;
}
