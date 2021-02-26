package cn.org.rookie.jeesdp.workflow.entity;

import lombok.Data;

import java.util.List;

@Data
public class Role {

    private String id;
    private String name;
    private List<User> users;
}
