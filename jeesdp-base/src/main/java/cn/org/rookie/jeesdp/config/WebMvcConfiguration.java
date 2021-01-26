package cn.org.rookie.jeesdp.config;

import cn.org.rookie.jeesdp.base.interceptor.PageInterceptor;
import cn.org.rookie.jeesdp.base.interceptor.PermissionInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.File;

@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/download/**").addResourceLocations("file:///" + System.getProperty("user.dir") + File.separator + "files");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new PermissionInterceptor());
        registry.addInterceptor(new PageInterceptor());
    }

}
