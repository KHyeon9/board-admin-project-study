package com.studyproject.boardadminproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@ConfigurationPropertiesScan
@SpringBootApplication
public class BoardAdminProjectStudyApplication {

    public static void main(String[] args) {
        SpringApplication.run(BoardAdminProjectStudyApplication.class, args);
    }

}
