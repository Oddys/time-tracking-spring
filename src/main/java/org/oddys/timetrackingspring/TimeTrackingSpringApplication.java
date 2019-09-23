package org.oddys.timetrackingspring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.Locale;

@SpringBootApplication
public class TimeTrackingSpringApplication {

    public static void main(String[] args) {
        SpringApplication.run(TimeTrackingSpringApplication.class, args);
    }
}
