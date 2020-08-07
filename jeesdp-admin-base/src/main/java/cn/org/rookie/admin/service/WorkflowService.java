package cn.org.rookie.admin.service;

import cn.org.rookie.workflow.entity.Department;
import cn.org.rookie.workflow.entity.Role;
import cn.org.rookie.workflow.entity.User;
import cn.org.rookie.workflow.service.impl.AbstractProcessService;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.stereotype.Service;

import java.util.List;

//@Service
public class WorkflowService extends AbstractProcessService {
    final SqlSessionFactory sqlSessionFactory;

    public WorkflowService(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }

    @Override
    public List<User> getUsers() {
        return null;
    }

    @Override
    public User getCurrentUser() {
        User user = new User();
        user.setId("id");
        user.setName("name");
        return user;
    }

    @Override
    public List<Role> getRoles() {
        return null;
    }

    @Override
    public List<Department> getDepartments() {
        return null;
    }

    @Override
    public Role getRoleById(String id) {
        return null;
    }

    @Override
    public Department getDepartmentById(String id) {
        return null;
    }
}
