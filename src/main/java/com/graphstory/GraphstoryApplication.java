package com.graphstory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.graphstory")
public class GraphstoryApplication  {

    public static void main(String[] args) {

        SpringApplication.run(GraphstoryApplication.class, args);
    }

}

