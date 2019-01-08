package com.graphstory.controller.rest;

import com.graphstory.service.CategoryService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedList;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
@RequestMapping("/api/category")
public class CategoryController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    CategoryService categoryService;

    // getStatuses
    @ApiOperation(value = "", nickname = "search categories", response = LinkedList.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successfully added Status"),
            @ApiResponse(code = 404, message = "Property not provided"),
            @ApiResponse(code = 500, message = "Failure of the system")
    })
    @RequestMapping( path = "/getCategoriesByStr/{s}", method = GET, produces = "application/json")
    public @ResponseBody
    LinkedList<String> search(@PathVariable String s) {
        return categoryService.search(s);
    }
}
