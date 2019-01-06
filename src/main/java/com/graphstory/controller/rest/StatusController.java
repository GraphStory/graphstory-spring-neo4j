package com.graphstory.controller.rest;

import com.graphstory.model.Status;
import com.graphstory.model.mapped.MappedStatus;
import com.graphstory.service.StatusService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    @ApiOperation(value = "/getStatuses/{userId}", nickname = "getStatuses", response = MappedStatus.class)
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
    public ResponseEntity delete(@PathVariable String statusId, @RequestParam String userId) {
        statusService.delete(statusId,userId);

        return new ResponseEntity(HttpStatus.OK);
    }
}
