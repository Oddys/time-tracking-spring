package org.oddys.timetrackingspring.config;

import org.modelmapper.ModelMapper;
import org.oddys.timetrackingspring.util.mapping.ActivityRecordMap;
import org.oddys.timetrackingspring.util.EntityMapper;
import org.oddys.timetrackingspring.util.PasswordManager;
import org.oddys.timetrackingspring.util.mapping.UserActivityMap;
import org.oddys.timetrackingspring.util.mapping.UserRequestDtoMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class ModelConfig implements WebMvcConfigurer {
    @Bean
    public PasswordManager passwordManager() {
        return new PasswordManager();
    }

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper =  new ModelMapper();
        modelMapper.addMappings(new UserActivityMap());
        modelMapper.addMappings(new ActivityRecordMap());
//        modelMapper.addMappings(new UserRequestDtoMap(passwordManager()));
        return modelMapper;
    }

    @Bean
    public EntityMapper entityMapper() {
        return new EntityMapper(passwordManager());
    }
}
