package com.graphstory.controller.view;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;


@Controller
public class PublicViewController extends GraphStoryViewController{

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @RequestMapping(value = { "/" }, method = RequestMethod.GET)
    public ModelAndView home(final HttpServletRequest req) {
        modelAndView = new ModelAndView("home");
        return modelAndView;
    }
}
