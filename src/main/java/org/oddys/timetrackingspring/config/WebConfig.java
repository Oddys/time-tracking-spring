package org.oddys.timetrackingspring.config;

import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.oddys.timetrackingspring.filter.AuthFilter;
import org.oddys.timetrackingspring.util.ActivityRecordMap;
import org.oddys.timetrackingspring.util.ConfigManager;
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
        registry.addViewController("/cabinet/activity-records")
                .setViewName("/cabinet/activity-records");
        registry.addViewController("/cabinet/activities")
                .setViewName("cabinet/activities");
        registry.addViewController("/cabinet/user-data")
                .setViewName("cabinet/user-data");
        registry.addViewController("/cabinet/user-activities")
                .setViewName("cabinet/user-activities");
    }

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper =  new ModelMapper();
        modelMapper.addMappings(new UserActivityMap());
        modelMapper.addMappings(new ActivityRecordMap());
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

    @Bean
    public ConfigManager configManager() {
        return new ConfigManager();
    }
}
