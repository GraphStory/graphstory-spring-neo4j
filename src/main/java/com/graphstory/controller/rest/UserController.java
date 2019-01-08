package com.graphstory.controller.rest;


import com.graphstory.model.User;
import com.graphstory.service.UserService;
import com.graphstory.util.model.ResponseString;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

@RestController
@RequestMapping("/api/user")
@Api(value="Users", description="Operations pertaining to users")
public class UserController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserService userService;

    // add user
    @ApiOperation(value = "", nickname = "create user", response = User.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successfully added User"),
            @ApiResponse(code = 404, message = "Property not provided"),
            @ApiResponse(code = 500, message = "Failure of the system")
    })
    @RequestMapping( path = "", method = POST, produces = "application/json")
    public @ResponseBody User create(@RequestParam String username) {
        return userService.create(username);
    }

    // edit user
    @ApiOperation(value = "", nickname = "update user", response = User.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "userId of user", required = true, dataType = "string", paramType = "path", defaultValue = "")
    })
    @ApiResponses(value = {
            @ApiResponse(code = 202, message = "Successfully updated User"),
            @ApiResponse(code = 404, message = "Property not provided"),
            @ApiResponse(code = 500, message = "Failure of the system")
    })
    @RequestMapping( path = "/{userId}", method = PUT, produces = "application/json")
    public @ResponseBody User update(@RequestParam String firstname, @RequestParam String lastname, @PathVariable String userId) {
        return userService.update(firstname,lastname,userId);
    }

    // find user by userId
    @ApiOperation(value = "", nickname = "findByUserId", response = User.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "userId of user", required = true, dataType = "string", paramType = "path", defaultValue = "")
    })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Failure")
    })
    @RequestMapping(method = RequestMethod.GET, path = "/{userId}", produces = "application/json")
    public @ResponseBody User findByUserId(@PathVariable String userId) {
        return userService.findByUserId(userId);
    }

    // find user by username
    @ApiOperation(value = "findByUsername", nickname = "findByUsername")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", value = "username of user", required = true, dataType = "string", paramType = "path", defaultValue = "")
    })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = User.class),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Failure")
    })
    @RequestMapping(method = RequestMethod.GET, path = "findByUsername/{username}", produces = "application/json")
    public @ResponseBody User findByUsername(@PathVariable String username) {
        return userService.findByUsername(username);
    }


    // follow user
    @ApiOperation(value = "follow", nickname = "follow")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "userId of user", required = true, dataType = "string", paramType = "path", defaultValue = ""),
            @ApiImplicitParam(name = "userIdToFollow", value = "userIdToFollow of user being followed", required = true, dataType = "string", paramType = "path", defaultValue = "")
    })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully updated User"),
            @ApiResponse(code = 404, message = "Property not provided"),
            @ApiResponse(code = 500, message = "Failure of the system")
    })
    @RequestMapping( path = "/follow/{userId}/{userIdToFollow}", method = GET, produces = "application/json")
    public ResponseString follow(@PathVariable String userId, @PathVariable String userIdToFollow) {
        userService.follow(userId,userIdToFollow);
        ResponseString rs= new ResponseString();
        rs.setResponse("success");
        return rs;
    }

    // unfollow a user
    @ApiOperation(value = "unfollow", nickname = "unfollow")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "userId of user", required = true, dataType = "string", paramType = "path", defaultValue = ""),
            @ApiImplicitParam(name = "userIdToUnFollow", value = "userIdToUnFollow of user being followed", required = true, dataType = "string", paramType = "path", defaultValue = "")
    })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully updated User"),
            @ApiResponse(code = 404, message = "Property not provided"),
            @ApiResponse(code = 500, message = "Failure of the system")
    })
    @RequestMapping( path = "/unfollow/{userId}/{userIdToUnFollow}", method = GET, produces = "application/json")
    public ResponseString unfollow(@PathVariable String userId, @PathVariable String userIdToUnFollow) {
        userService.unfollow(userId,userIdToUnFollow);
        ResponseString rs= new ResponseString();
        rs.setResponse("success");
        return rs;
    }


    // followers
    @ApiOperation(value = "followers", nickname = "followers")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "userId of user", required = true, dataType = "string", paramType = "path", defaultValue = "")
    })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Failure")
    })
    @RequestMapping(method = RequestMethod.GET, path = "/followers/{userId}", produces = "application/json")
    public @ResponseBody Set<User> followers(@PathVariable String userId) {
        return userService.whoIsFollowingUser(userId);
    }

    // following
    @ApiOperation(value = "following", nickname = "following")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "userId of user", required = true, dataType = "string", paramType = "path", defaultValue = "")
    })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Failure")
    })
    @RequestMapping(method = RequestMethod.GET, path = "/following/{userId}", produces = "application/json")
    public @ResponseBody Set<User> following(@PathVariable String userId) {
        return userService.whoIsUserFollowing(userId);
    }


    // a zip code to a user
    @ApiOperation(value = "", nickname = "add zip code")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "userId of user", required = true, dataType = "string", paramType = "path", defaultValue = ""),
            @ApiImplicitParam(name = "zip", value = "zip code of user", required = true, dataType = "string", paramType = "path", defaultValue = "")
    })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully added zip code User"),
            @ApiResponse(code = 404, message = "Property not provided"),
            @ApiResponse(code = 500, message = "Failure of the system")
    })
    @RequestMapping( path = "/addzip/{userId}/{zip}", method = GET, produces = "application/json")
    public ResponseString addZip(@PathVariable String userId, @PathVariable String zip) {
        userService.addZip(userId,zip);
        ResponseString rs= new ResponseString();
        rs.setResponse("success");
        return rs;
    }


    //TODO
    // words connected to a user (searches and tags)

    //TODO
    // tags connected to a user

    //TODO
    // searches connected to a user
}