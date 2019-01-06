package com.graphstory.controller.rest;


import com.graphstory.model.User;
import com.graphstory.service.AuthService;
import com.graphstory.util.GraphStoryConstants;
import com.graphstory.util.SessionUtils;
import com.graphstory.util.model.ResponseString;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    AuthService authService;

    //login
    @ApiOperation(value = "login", nickname = "login", response = User.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successfully Logged in User"),
            @ApiResponse(code = 404, message = "Not Found or property not provided"),
            @ApiResponse(code = 500, message = "Failure of the system")
    })
    @RequestMapping( path = "login", method = POST, produces = "application/json")
    public @ResponseBody
    ResponseString login(@RequestParam String username, final HttpServletRequest request) {

        User user =  authService.login(username);
        ResponseString rs= new ResponseString();
        if(user!=null){
            rs.setResponse("success");
            SessionUtils.set(request,GraphStoryConstants.userId,user.getUserId());
            SessionUtils.set(request,GraphStoryConstants.username,user.getUsername());

            //request.getSession().setMaxInactiveInterval(GraphStoryConstants.sessionTimeout);
        }else{
            rs.setResponse("fail");
        }
        return rs;
    }

}