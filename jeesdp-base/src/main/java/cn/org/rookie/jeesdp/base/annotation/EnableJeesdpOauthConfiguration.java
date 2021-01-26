package cn.org.rookie.jeesdp.base.annotation;

import cn.org.rookie.jeesdp.base.component.LogAspect;
import cn.org.rookie.jeesdp.base.configuration.SecurityConfiguration;
import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@AutoConfigurationPackage
@Import({SecurityConfiguration.class, SecurityConfiguration.ResourceServerConfiguration.class, LogAspect.class})
public @interface EnableJeesdpOauthConfiguration {
}
