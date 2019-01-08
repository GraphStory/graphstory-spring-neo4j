package com.graphstory.controller.rest;

import com.graphstory.model.Status;
import com.graphstory.model.mapped.MappedStatus;
import com.graphstory.service.StatusService;
import com.graphstory.util.model.ResponseString;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.*;

@RestController
@RequestMapping("/api/status")
public class StatusController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());


    @Autowired
    StatusService statusService;

    // getStatuses
    @ApiOperation(value = "", nickname = "getStatuses", response = MappedStatus.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successfully added Status"),
            @ApiResponse(code = 404, message = "Property not provided"),
            @ApiResponse(code = 500, message = "Failure of the system")
    })
    @RequestMapping( path = "/getStatuses/{userId}", method = GET, produces = "application/json")
    public @ResponseBody List<MappedStatus> getStatuses(@PathVariable String userId) {
        return statusService.getStatuses(userId);
    }

    // create a status
    @ApiOperation(value = "", nickname = "create status", response = Status.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successfully added Status"),
            @ApiResponse(code = 404, message = "Property not provided"),
            @ApiResponse(code = 500, message = "Failure of the system")
    })
    @RequestMapping( path = "", method = POST, produces = "application/json")
    public @ResponseBody Status create(@RequestParam String statusText,@RequestParam String tagstr,@RequestParam String userId) {
        return statusService.create(statusText,tagstr,userId);
    }

    //update status
    @ApiOperation(value = "", nickname = "update status", response = Status.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successfully updated Status"),
            @ApiResponse(code = 404, message = "Property not provided"),
            @ApiResponse(code = 500, message = "Failure of the system")
    })
    @RequestMapping( path = "/{statusId}", method = PUT, produces = "application/json")
    public @ResponseBody Status update(@RequestParam String statusText, @RequestParam String tagstr, @PathVariable String statusId, @RequestParam String userId) {
        return statusService.update(statusText,tagstr,statusId,userId);
    }

    //delete status
    @ApiOperation(value = "", nickname = "delete status", response = Status.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successfully deleted Status"),
            @ApiResponse(code = 404, message = "Property not provided"),
            @ApiResponse(code = 500, message = "Failure of the system")
    })
    @RequestMapping( path = "/{statusId}", method = DELETE, produces = "application/json")
    public @ResponseBody ResponseString  delete(@PathVariable String statusId, @RequestParam String userId) {
        statusService.delete(statusId,userId);

        ResponseString rs= new ResponseString();
        rs.setResponse("success");
        return rs;
    }

    //like status
    @ApiOperation(value = "", nickname = "like status", response = Status.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully liked Status"),
            @ApiResponse(code = 404, message = "Property not provided"),
            @ApiResponse(code = 500, message = "Failure of the system")
    })
    @RequestMapping( path = "/like/{statusId}/{userId}", method = GET, produces = "application/json")
    public @ResponseBody ResponseString like(@PathVariable String statusId, @PathVariable String userId) {

        statusService.like(statusId,userId);
        ResponseString rs= new ResponseString();
        rs.setResponse("success");
        return rs;
    }

    //unlike status
    @ApiOperation(value = "", nickname = "unlike status", response = Status.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully unliked Status"),
            @ApiResponse(code = 404, message = "Property not provided"),
            @ApiResponse(code = 500, message = "Failure of the system")
    })
    @RequestMapping( path = "/unlike/{statusId}/{userId}", method = GET, produces = "application/json")
    public @ResponseBody ResponseString unlike(@PathVariable String statusId, @PathVariable String userId) {
        statusService.unlike(statusId,userId);
        ResponseString rs= new ResponseString();
        rs.setResponse("success");
        return rs;
    }

}
