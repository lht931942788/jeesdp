package cn.org.rookie.jeesdp.base.plugin;

import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Invocation;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CodePlugin implements ApplicationRunner, Interceptor {

    private static final Map<String, List<Object>> CODE_MAP = new HashMap<>();

    @Override
    public void run(ApplicationArguments args) throws Exception {

    }

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        return null;
    }
}
