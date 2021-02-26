package cn.org.rookie.annotation;

//import cn.org.rookie.jeesdp.common.configuration.SecurityConfiguration;

import org.springframework.boot.autoconfigure.AutoConfigurationPackage;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@AutoConfigurationPackage
//@Import({SecurityConfiguration.class})
public @interface EnableJeesdpOauthConfiguration {
}
