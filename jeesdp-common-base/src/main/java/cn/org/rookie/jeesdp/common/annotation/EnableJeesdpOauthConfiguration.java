package cn.org.rookie.jeesdp.common.annotation;

import cn.org.rookie.jeesdp.common.configuration.SecurityConfiguration;
import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@AutoConfigurationPackage
@Import({SecurityConfiguration.class})
public @interface EnableJeesdpOauthConfiguration {
}
