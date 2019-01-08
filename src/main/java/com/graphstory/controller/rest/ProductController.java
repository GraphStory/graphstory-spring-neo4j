package com.graphstory.controller.rest;

import com.graphstory.model.Product;
import com.graphstory.model.mapped.MappedProduct;
import com.graphstory.service.ProductService;
import com.graphstory.util.model.ResponseString;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
@RequestMapping("/api/product")
public class ProductController {


    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    ProductService productService;



    //clicked product
    @ApiOperation(value = "", nickname = "getProductForView", response = Product.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully clicked product"),
            @ApiResponse(code = 404, message = "Property not provided"),
            @ApiResponse(code = 500, message = "Failure of the system")
    })
    @RequestMapping( path = "/{productId}}", method = GET, produces = "application/json")
    public @ResponseBody MappedProduct getProductForView(@RequestParam String productId) {
        return productService.getProductForView(productId);
    }

    //clicked product
    @ApiOperation(value = "", nickname = "clicked product", response = Product.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully clicked product"),
            @ApiResponse(code = 404, message = "Property not provided"),
            @ApiResponse(code = 500, message = "Failure of the system")
    })
    @RequestMapping( path = "/click/{productId}/{userId}", method = GET, produces = "application/json")
    public @ResponseBody
    ResponseString clicked(@PathVariable String productId, @PathVariable String userId) {
        productService.click(productId,userId);
        ResponseString rs= new ResponseString();
        rs.setResponse("success");
        return rs;
    }

    //like product
    @ApiOperation(value = "", nickname = "like product", response = Product.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully liked product"),
            @ApiResponse(code = 404, message = "Property not provided"),
            @ApiResponse(code = 500, message = "Failure of the system")
    })
    @RequestMapping( path = "/like/{productId}/{userId}", method = GET, produces = "application/json")
    public @ResponseBody
    ResponseString like(@PathVariable String productId, @PathVariable String userId) {
        productService.like(productId,userId);
        ResponseString rs= new ResponseString();
        rs.setResponse("success");
        return rs;
    }

    //unlike product
    @ApiOperation(value = "", nickname = "unlike product", response = Product.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully unliked product"),
            @ApiResponse(code = 404, message = "Property not provided"),
            @ApiResponse(code = 500, message = "Failure of the system")
    })
    @RequestMapping( path = "/unlike/{productId}/{userId}", method = GET, produces = "application/json")
    public @ResponseBody ResponseString unlike(@PathVariable String productId, @PathVariable String userId) {
        productService.unlike(productId,userId);
        ResponseString rs= new ResponseString();
        rs.setResponse("success");
        return rs;
    }


}
