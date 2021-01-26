package cn.org.rookie.jeesdp.workflow.service;

import cn.org.rookie.jeesdp.workflow.definitions.AbstractModel;
import cn.org.rookie.jeesdp.workflow.definitions.Process;
import cn.org.rookie.jeesdp.workflow.entity.Department;
import cn.org.rookie.jeesdp.workflow.entity.Role;
import cn.org.rookie.jeesdp.workflow.entity.Task;
import cn.org.rookie.jeesdp.workflow.entity.User;

import java.util.Date;
import java.util.List;

public interface ProcessService {

    List<User> getUsers();

    User getCurrentUser();

    List<Role> getRoles();

    List<Department> getDepartments();

    Role getRoleById(String id);

    Department getDepartmentById(String id);

    List<Process> selectProcesses();

    Process selectProcessById(String id);

    int insertProcess(Process process);

    int updateProcess(Process process);

    int deleteProcessById(String id);

    int updateProcessContent(String content, String id);

    void insertTask(Task task);

    boolean insertLog(String taskId, AbstractModel current, String operateName, int status, boolean isInsertUser);

    Task selectTaskById(String id);

    boolean updateTask(String taskId, AbstractModel node, int i);

    void insertTaskUser(String[] userIds, String taskId);

    boolean updateTask(String taskId, int status, Date now);

    boolean deleteTaskUser(String taskId);
}
