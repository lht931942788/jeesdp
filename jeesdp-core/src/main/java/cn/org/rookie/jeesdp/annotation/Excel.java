package cn.org.rookie.jeesdp.annotation;

public @interface Excel {

    String title();

    short order();

    String pattern() default "";
}
