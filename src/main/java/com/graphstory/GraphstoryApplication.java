package com.graphstory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootApplication(scanBasePackages = "com.graphstory")
public class GraphstoryApplication extends WebMvcConfigurerAdapter {

    public static void main(String[] args) {

        SpringApplication.run(GraphstoryApplication.class, args);
    }

}

