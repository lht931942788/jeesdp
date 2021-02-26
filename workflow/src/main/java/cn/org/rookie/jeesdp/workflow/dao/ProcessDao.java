package cn.org.rookie.jeesdp.workflow.dao;

import cn.org.rookie.jeesdp.workflow.definitions.AbstractModel;
import cn.org.rookie.jeesdp.workflow.definitions.Process;
import cn.org.rookie.jeesdp.workflow.entity.Log;
import cn.org.rookie.jeesdp.workflow.entity.Task;
import cn.org.rookie.jeesdp.workflow.entity.TaskUser;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class ProcessDao {
    private static final org.apache.commons.logging.Log logger = LogFactory.getLog(ProcessDao.class);
    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    public List<Process> selectProcesses() {
        String sql = "SELECT wp.*, wc.content FROM workflow_process wp,workflow_content wc WHERE wc.process_id = wp.id";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Process.class));
    }

    public Process selectProcessById(String id) {
        String sql = "SELECT wp.*, wc.content FROM workflow_process wp,workflow_content wc WHERE wp.id = :id AND wc.process_id = wp.id";
        MapSqlParameterSource parameter = new MapSqlParameterSource();
        parameter.addValue("id", id);
        logger.debug(parameter);
        return jdbcTemplate.queryForObject(sql, parameter, new BeanPropertyRowMapper<>(Process.class));
    }

    public int insertProcess(Process process) {
        String sql = "INSERT INTO workflow_process (id, name, content, params, create_time, status) VALUES (:id, :name, :content, :params, :createTime, :status)";
        BeanPropertySqlParameterSource parameter = new BeanPropertySqlParameterSource(process);
        logger.debug(parameter);
        return jdbcTemplate.update(sql, parameter);
    }

    public int updateProcess(Process process) {
        String sql = "UPDATE workflow_process SET name = :name, params = :params, status = :status WHERE id = :id";
        BeanPropertySqlParameterSource parameter = new BeanPropertySqlParameterSource(process);
        logger.debug(parameter);
        return jdbcTemplate.update(sql, parameter);
    }

    public int deleteProcessById(String id) {
        String sql = "DELETE workflow_process WHERE id = :id";
        MapSqlParameterSource parameter = new MapSqlParameterSource();
        parameter.addValue("id", id);
        logger.debug(parameter);
        return jdbcTemplate.update(sql, parameter);
    }

    public int insertProcessContent(String processId, String xml) {
        String sql = "INSERT INTO workflow_process_content (id, process_id, content) values (:id, :processId, :content)";
        MapSqlParameterSource parameter = new MapSqlParameterSource();
        parameter.addValue("id", UUID());
        parameter.addValue("processId", processId);
        parameter.addValue("content", xml);
        logger.debug(parameter);
        return jdbcTemplate.update(sql, parameter);
    }

    public int deleteProcessContentById(String processId) {
        String sql = "DELETE workflow_process_content WHERE process_id = :id";
        MapSqlParameterSource parameter = new MapSqlParameterSource();
        parameter.addValue("id", processId);
        logger.debug(parameter);
        return jdbcTemplate.update(sql, parameter);
    }

    public int updateProcessContent(String content, String id) {
        String sql = "UPDATE workflow_process_content SET content = :content WHERE id = :id";
        MapSqlParameterSource parameter = new MapSqlParameterSource();
        parameter.addValue("id", id);
        parameter.addValue("content", content);
        logger.debug(parameter);
        return jdbcTemplate.update(sql, parameter);
    }

    public Task selectTaskById(String id) {
        String sql = "SELECT id, process_id, process_name, current_node_id, current_node_name, status, start_time, end_time, creator FROM workflow_task WHERE id = :id";
        MapSqlParameterSource parameter = new MapSqlParameterSource();
        parameter.addValue("id", id);
        logger.debug(parameter);
        return jdbcTemplate.queryForObject(sql, parameter, new BeanPropertyRowMapper<>(Task.class));
    }

    public int insertTask(Task task) {
        String sql = "INSERT INTO workflow_task (id, process_id, process_name, current_node_id, current_node_name, status, start_time, end_time, creator) " +
                "VALUES (:id, :processId, :processName, :currentNodeId, :currentNodeName, :status, :startTime, :endTime, :creator)";
        BeanPropertySqlParameterSource parameter = new BeanPropertySqlParameterSource(task);
        logger.debug(parameter);
        return jdbcTemplate.update(sql, parameter);
    }

    public int updateTask(String taskId, AbstractModel current, int status) {
        String sql = "UPDATE workflow_task SET current_node_id = :currentNodeId, current_node_name = :currentNodeName, status = :status where id = :id";
        MapSqlParameterSource parameter = new MapSqlParameterSource();
        parameter.addValue("id", taskId);
        parameter.addValue("currentNodeId", current.getId());
        parameter.addValue("currentNodeName", current.getName());
        parameter.addValue("status", status);
        logger.debug(parameter);
        return jdbcTemplate.update(sql, parameter);
    }

    public int updateTask(String taskId, int status, Date endTime) {
        String sql = "UPDATE workflow_task SET status = :status, end_time = :endTime where id = :id";
        MapSqlParameterSource parameter = new MapSqlParameterSource();
        parameter.addValue("id", taskId);
        parameter.addValue("status", status);
        parameter.addValue("endTime", endTime);
        logger.debug(parameter);
        return jdbcTemplate.update(sql, parameter);
    }

    public void insertTaskUser(String[] ids, String taskId) {
        deleteTaskUser(taskId);
        String sql = "INSERT INTO workflow_task_user (id, task_id, user_id) VALUES (:id, :taskId, :userId)";
        List<SqlParameterSource> params = new ArrayList<>();
        for (String id : ids) {
            MapSqlParameterSource parameter = new MapSqlParameterSource();
            parameter.addValue("id", UUID());
            parameter.addValue("taskId", taskId);
            parameter.addValue("userId", id);
            params.add(parameter);
        }
        logger.debug(params);
        jdbcTemplate.batchUpdate(sql, params.toArray(new SqlParameterSource[0]));
    }

    public int updateTaskUser(TaskUser taskUser) {
        String sql = "UPDATE workflow_task_user SET status = :status WHERE task_id = :taskId and user_id = :userId";
        BeanPropertySqlParameterSource parameter = new BeanPropertySqlParameterSource(taskUser);
        logger.debug(parameter);
        return jdbcTemplate.update(sql, parameter);
    }

    public int deleteTaskUser(String taskId) {
        String sql = "DELETE FROM workflow_task_user WHERE task_id = :taskId";
        MapSqlParameterSource parameter = new MapSqlParameterSource();
        parameter.addValue("taskId", taskId);
        logger.debug(parameter);
        return jdbcTemplate.update(sql, parameter);
    }

    public int insertLog(Log log) {
        String sql = "INSERT INTO workflow_log (id, task_id, node_id, node_name, operate_name, operator_id, operator_name, remark, status, create_time) VALUES (:id, :taskId, :nodeId, :nodeName, :operateName, :operatorId, :operatorName, :remark, :status, :createTime)";
        BeanPropertySqlParameterSource parameter = new BeanPropertySqlParameterSource(log);
        logger.debug(parameter);
        return jdbcTemplate.update(sql, parameter);
    }

    public String UUID() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

}
