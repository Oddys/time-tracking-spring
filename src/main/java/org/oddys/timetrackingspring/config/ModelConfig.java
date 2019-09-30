package org.oddys.timetrackingspring.config;

import org.modelmapper.ModelMapper;
import org.oddys.timetrackingspring.util.ActivityRecordMap;
import org.oddys.timetrackingspring.util.EntityMapper;
import org.oddys.timetrackingspring.util.PasswordManager;
import org.oddys.timetrackingspring.util.UserActivityMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class ModelConfig implements WebMvcConfigurer {
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
    public EntityMapper entityMapper() {
        return new EntityMapper(passwordManager());
    }
}
