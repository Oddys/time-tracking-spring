package org.oddys.timetrackingspring.config;

import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.oddys.timetrackingspring.filter.AuthFilter;
import org.oddys.timetrackingspring.util.AttributeSetter;
import org.oddys.timetrackingspring.util.EntityMapper;
import org.oddys.timetrackingspring.util.ParameterValidator;
import org.oddys.timetrackingspring.util.PasswordManager;
import org.oddys.timetrackingspring.util.UserActivityMap;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@Slf4j
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("index");
        registry.addViewController("/index").setViewName("index");
        registry.addViewController("/cabinet").setViewName("cabinet");
        registry.addViewController("/activities").setViewName("activities");
        registry.addViewController("/cabinet/user-data").setViewName("user-data");
        registry.addViewController("/cabinet/user-activities")
                .setViewName("cabinet/user-activities");
    }

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper =  new ModelMapper();
        modelMapper.addMappings(new UserActivityMap());
        return modelMapper;
    }

    @Bean
    public PasswordManager passwordManager() {
        return new PasswordManager();
    }

    @Bean
    public ParameterValidator parameterValidator() {
        return new ParameterValidator();
    }

    @Bean
    public AttributeSetter attributeSetter() {
        return new AttributeSetter();
    }

    @Bean
    public EntityMapper entityMapper() {
        return new EntityMapper(passwordManager());
    }

    @Bean
    public FilterRegistrationBean<AuthFilter> authFilter() {
        FilterRegistrationBean<AuthFilter> bean = new FilterRegistrationBean<>();
        bean.setFilter(new AuthFilter());
        bean.addUrlPatterns("/cabinet/*");
        return bean;
    }
}
