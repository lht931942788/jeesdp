package cn.org.rookie.common.plugin;

import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Invocation;
import org.springframework.beans.BeansException;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

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