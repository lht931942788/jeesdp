package cn.org.rookie.jeesdp.core.annotation;

public @interface Excel {

    String title();

    short order();

    String pattern() default "";
}
