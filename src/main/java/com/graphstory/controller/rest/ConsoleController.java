package com.graphstory.controller.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/console")
public class ConsoleController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
}
