package cn.org.rookie.jeesdp.core.service;

import cn.org.rookie.jeesdp.core.mapper.ApiMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class ApiService {

    @Autowired
    private ApiMapper apiMapper;

    public Object save(String name, Map<String, Object> param) {
        String id = UUID.randomUUID().toString().replace("-", "").toLowerCase();
        int i = apiMapper.insert(name, param);
        return null;
    }

    public Object select(String name, Object id) {
        return apiMapper.select(name, id);
    }

    public List<?> select(String name, Map<String, Object> param) {
        return apiMapper.select(name, param);
    }

    public Object upadte(String name, HashMap<String, Object> param) {
        return apiMapper.update(name, param);
    }
}
