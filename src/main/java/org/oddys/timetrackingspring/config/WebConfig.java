package org.oddys.timetrackingspring.config;

import org.modelmapper.ModelMapper;
import org.oddys.timetrackingspring.util.ParameterValidator;
import org.oddys.timetrackingspring.util.PasswordManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("index");
        registry.addViewController("/index").setViewName("index");
        registry.addViewController("/cabinet").setViewName("cabinet");
        registry.addViewController("/activities").setViewName("activities");
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public PasswordManager passwordManager() {
        return new PasswordManager();
    }

    @Bean
    public ParameterValidator parameterValidator() {
        return new ParameterValidator();
    }
}
