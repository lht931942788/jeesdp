package cn.org.rookie.jeesdp.mapper.annotation;

import cn.org.rookie.jeesdp.mapper.BaseMapper;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface JoinTable {

    Class<? extends BaseMapper<?, ?>> mappedClass();

    JoinColumn joinColumn();

    boolean isCollection() default true;
}
