package org.oddys.timetrackingspring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class TimeTrackingSpringApplication implements WebMvcConfigurer {
    public static void main(String[] args) {
        SpringApplication.run(TimeTrackingSpringApplication.class, args);
    }
}
