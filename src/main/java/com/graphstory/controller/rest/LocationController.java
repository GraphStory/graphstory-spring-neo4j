package com.graphstory.controller.rest;


import com.graphstory.model.Location;
import com.graphstory.model.Status;
import com.graphstory.service.LocationService;
import com.graphstory.util.model.ResponseString;
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

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
@RequestMapping("/api/location")
public class LocationController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    LocationService locationService;

    @ApiOperation(value = "", nickname = "get location", response = Location.class)
    @RequestMapping (path = "/getsomething/{locationId}", method= GET)
    public @ResponseBody
    Location getANewLocation(@PathVariable String locationId){
        return locationService.getloc(locationId);
    }

    //viewed location
    @ApiOperation(value = "", nickname = "viewed location", response = Status.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully viewed location"),
            @ApiResponse(code = 404, message = "Property not provided"),
            @ApiResponse(code = 500, message = "Failure of the system")
    })
    @RequestMapping( path = "/view/{locationId}/{userId}", method = GET, produces = "application/json")
    public @ResponseBody
    ResponseString viewed(@PathVariable String locationId, @PathVariable String userId) {

        locationService.viewed(locationId,userId);
        ResponseString rs= new ResponseString();
        rs.setResponse("success");
        return rs;
    }

    // we are doing these here because they need to be indepent of their locations
    // product should post, status should post, then a location should be attached
    // will reduce the potential for a fail on the product or status
    //TODO:
    // add product to location

    //TODO:
    // add status to an existing location

    //TODO:
    // add status to an new location

    //TODO: add a new locations using some geo service
}
