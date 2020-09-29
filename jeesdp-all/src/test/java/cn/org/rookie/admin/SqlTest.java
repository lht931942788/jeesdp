package cn.org.rookie.admin;

import cn.org.rookie.common.base.entity.User;
import org.apache.ibatis.jdbc.SQL;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeanUtils;

import java.util.HashMap;
import java.util.Map;

public class SqlTest {

    @Test
    public void sqlTest() {
        SQL sql = new SQL();
        sql.WHERE("demo = 1");
        System.out.println(sql.toString());
        Map<String, Object> map = new HashMap<>();
        map.put("id", "ddd");
        User demo = new User();
        demo.setId(2222 + "");
        User target = new User();
        BeanUtils.copyProperties(map, target);
        System.out.println(target);
    }
}
