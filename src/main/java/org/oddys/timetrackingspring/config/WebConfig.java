package org.oddys.timetrackingspring.config;

import lombok.extern.slf4j.Slf4j;
import org.oddys.timetrackingspring.filter.AuthFilter;
import org.oddys.timetrackingspring.util.ConfigManager;
import org.oddys.timetrackingspring.util.ParameterValidator;
import org.oddys.timetrackingspring.util.RequestParametersEncoder;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.charset.StandardCharsets;

@Configuration
@Slf4j
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("index");
        registry.addViewController("/index").setViewName("index");
        registry.addViewController("/error").setViewName("error");
        registry.addViewController("/cabinet/user-data")
                .setViewName("cabinet/user-data");
    }

    @Bean
    public ParameterValidator parameterValidator() {
        return new ParameterValidator();
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

    @Bean
    public RequestParametersEncoder paramEncoder() {
        return new RequestParametersEncoder(StandardCharsets.UTF_8.toString());
    }
}
