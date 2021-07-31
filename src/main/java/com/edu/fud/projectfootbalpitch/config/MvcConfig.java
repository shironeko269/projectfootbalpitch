package com.edu.fud.projectfootbalpitch.config;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Configuration
public class MvcConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/css/**").addResourceLocations("classpath:/static/css/");
        registry.addResourceHandler("/resources/ajax/**").addResourceLocations("classpath:/static/ajax/");
        registry.addResourceHandler("/resources/documentation/**").addResourceLocations("classpath:/static/documentation/");
        registry.addResourceHandler("/resources/images/**").addResourceLocations("classpath:/static/images/");
        registry.addResourceHandler("/resources/js/**").addResourceLocations("classpath:/static/js/");
        registry.addResourceHandler("/resources/plugins/**").addResourceLocations("classpath:/static/plugins/");
        registry.addResourceHandler("/resources/scss/**").addResourceLocations("classpath:/static/scss/");
        registry.addResourceHandler("/resources/usercssjs/**").addResourceLocations("classpath:/static/usercssjs/");
    }
}