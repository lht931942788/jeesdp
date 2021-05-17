package cn.org.rookie.jeesdp.jeesdp.workflow.service.impl;

import cn.org.rookie.jeesdp.jeesdp.workflow.dao.ProcessDao;
import cn.org.rookie.jeesdp.jeesdp.workflow.definitions.AbstractModel;
import cn.org.rookie.jeesdp.jeesdp.workflow.definitions.Process;
import cn.org.rookie.jeesdp.jeesdp.workflow.entity.*;
import cn.org.rookie.jeesdp.jeesdp.workflow.service.ProcessService;

import java.util.Date;
import java.util.List;

public abstract class AbstractProcessService implements ProcessService {

    //@Autowired
    protected ProcessDao processDao;

    @Override
    public abstract List<User> getUsers();

    @Override
    public abstract User getCurrentUser();

    @Override
    public abstract List<Role> getRoles();

    @Override
    public abstract List<Department> getDepartments();

    @Override
    public abstract Role getRoleById(String id);

    @Override
    public abstract Department getDepartmentById(String id);

    @Override
    public List<Process> selectProcesses() {
        return processDao.selectProcesses();
    }

    @Override
    public Process selectProcessById(String id) {
        return processDao.selectProcessById(id);
    }

    @Override
    public int insertProcess(Process process) {
        return processDao.insertProcess(process);
    }

    @Override
    public int updateProcess(Process process) {
        return processDao.updateProcess(process);
    }

    @Override
    public int deleteProcessById(String id) {
        return processDao.deleteProcessById(id);
    }

    @Override
    public int updateProcessContent(String content, String id) {
        return processDao.updateProcessContent(content, id);
    }

    @Override
    public void insertTask(Task task) {
        processDao.insertTask(task);
    }

    @Override
    public boolean insertLog(String taskId, AbstractModel current, String operateName, int status, boolean isInsertUser) {
        Log log = new Log();
        log.setId(UUID());
        log.setTaskId(taskId);
        log.setNodeId(current.getId());
        log.setNodeName(current.getName());
        log.setStatus(status);
        log.setCreateTime(new Date());
        log.setOperateName(operateName);
        processDao.insertLog(log);
        return true;
    }

    @Override
    public Task selectTaskById(String id) {
        return processDao.selectTaskById(id);
    }

    public boolean updateTask(String taskId, AbstractModel node, int i) {
        return processDao.updateTask(taskId, node, i) == 1;
    }

    @Override
    public void insertTaskUser(String[] userIds, String taskId) {
        processDao.insertTaskUser(userIds, taskId);
    }

    @Override
    public boolean updateTask(String taskId, int status, Date now) {
        return processDao.updateTask(taskId, status, now) == 1;
    }

    @Override
    public boolean deleteTaskUser(String taskId) {
        return processDao.deleteTaskUser(taskId) > 0;
    }

    public String UUID() {
        return processDao.UUID();
    }

}
