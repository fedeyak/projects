package com.example.student_organiser.configuration;

import com.example.student_organiser.service.StudentService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnProperty(name = "app.create-initial-students", havingValue = "true")
public class StudentOrganiserConfig {

    @Bean
    public ApplicationListener<ContextRefreshedEvent> initialDataLoader(StudentService studentService) {
        return event -> {
            studentService.addStudent("Jet", "Lee", 17);
            studentService.addStudent("Simeon", "Stolpnik", 91);
        };
    }
}
