package cn.org.rookie.common.interceptor;

import cn.org.rookie.mapper.BaseMapper;
import cn.org.rookie.mapper.annotation.JoinTable;
import cn.org.rookie.mapper.sql.where.Wrapper;
import cn.org.rookie.mapper.utils.JoinTableFlag;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Properties;

@Intercepts({@Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class})})
public class JoinTableInterceptor implements Interceptor, ApplicationContextAware {

    private ApplicationContext applicationContext;

    @SuppressWarnings({"unchecked", "rawtypes"})
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        Object result = invocation.proceed();
        Method method = invocation.getMethod(); //代理方法
        Class<?> returnType = method.getReturnType();
        if (returnType.isAssignableFrom(List.class)) {
            Object[] args = invocation.getArgs(); //方法参数
            MappedStatement mappedStatement = (MappedStatement) args[0];
            String id = mappedStatement.getId();
            String resource = id.substring(0, id.lastIndexOf("."));
            Class type = (Class) ((ParameterizedType) (Class.forName(resource).getGenericInterfaces()[0])).getActualTypeArguments()[0];
            Field[] fields = type.getDeclaredFields();
            for (Field field : fields) {
                JoinTable joinTable = field.getAnnotation(JoinTable.class);
                if (joinTable != null) {
                    field.setAccessible(true);
                    BaseMapper baseMapper = applicationContext.getBean(joinTable.mappedClass());
                    if (joinTable.isCollection()) {
                        List list = (List) result;
                        list.forEach(row -> {
                            try {
                                //TODO 加查询条件
                                Wrapper wrapper = Wrapper.build();
                                Object obj = field.get(row);
                                wrapper.eq(field.getName(), obj);
                                field.set(row, baseMapper.selectList(wrapper));
                            } catch (IllegalAccessException e) {
                                e.printStackTrace();
                            }
                        });
                    } else {
                        Wrapper wrapper = Wrapper.build();
                        Object obj = field.get(result);
                        wrapper.eq(field.getName(), obj);
                        field.set(result, baseMapper.selectList(wrapper));
                    }
                }
            }
        }
        return result;
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {

    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
