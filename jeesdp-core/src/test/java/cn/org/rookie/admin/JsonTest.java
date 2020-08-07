package cn.org.rookie.admin;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.JsonPointer;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest
public class JsonTest {
    @Autowired
    ObjectMapper objectMapper;

    @Test
    public void jackson() throws JsonProcessingException {
        Map<String, Object> map = new HashMap<>();
        map.put("js5", 1);
        Map<String, Object> map2 = new HashMap<>();
        map2.put("jsj", 3);
        map.put("dd", map2);
        JsonNode jsonNode = objectMapper.readTree(objectMapper.writeValueAsString(map));
        System.out.println(jsonNode.toString());
        JsonNode jsonNodePath = jsonNode.at(JsonPointer.compile("/dd/jsj"));
        long l = jsonNodePath.asInt();
        boolean empty = jsonNodePath.isEmpty();
        System.out.println(l);
        System.out.println(empty);
    }

    @Test
    public void fastjson() throws JsonProcessingException {
        Map<String, Object> map = new HashMap<>();
        map.put("js5", 1);
        Map<String, Object> map2 = new HashMap<>();
        map2.put("jsj", 3);
        map.put("dd", map2);
        JSONObject jsonObject = JSON.parseObject(JSON.toJSONString(map));
        Object jsj = jsonObject.getBigInteger("/dd/jsj");
        System.out.println(jsj);
    }
}
