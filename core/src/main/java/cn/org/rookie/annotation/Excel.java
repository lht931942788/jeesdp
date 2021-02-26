package cn.org.rookie.annotation;

public @interface Excel {

    String title();

    short order();

    String pattern() default "";
}
