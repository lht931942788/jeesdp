package cn.org.rookie.common.annotation;

import cn.org.rookie.common.component.LogAspect;
import cn.org.rookie.common.configuration.SecurityConfiguration;
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
