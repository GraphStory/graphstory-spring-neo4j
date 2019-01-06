package com.graphstory.controller.view;

import com.graphstory.model.mapped.MappedStatus;
import com.graphstory.service.StatusService;
import com.graphstory.util.GraphStoryConstants;
import com.graphstory.util.SessionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/app")
public class SecureViewController extends GraphStoryViewController{

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    StatusService statusService;


    @RequestMapping(value = { "/home" }, method = RequestMethod.GET)
    public ModelAndView home(final HttpServletRequest req) {
        modelAndView = new ModelAndView("apphome");

        defaultModelAndViewObjects(req,"Home");
        return modelAndView;
    }

    @RequestMapping(value = { "/timeline" }, method = RequestMethod.GET)
    public ModelAndView timeline(final HttpServletRequest req) {
        modelAndView = new ModelAndView("timeline");


        logger.info(SessionUtils.get(req,GraphStoryConstants.userId).toString());

        List<MappedStatus> mappedStatuses = statusService.getStatuses(SessionUtils.get(req,GraphStoryConstants.userId).toString());

        //

        modelAndView.addObject("statuses", mappedStatuses);

        defaultModelAndViewObjects(req,"Social Timeline");
        return modelAndView;
    }

    @RequestMapping(value = { "/friendFinder" }, method = RequestMethod.GET)
    public ModelAndView friendFinder(final HttpServletRequest req) {
        modelAndView = new ModelAndView("friendFinder");

        defaultModelAndViewObjects(req,"Friend Finder");
        return modelAndView;
    }

    @RequestMapping(value = { "/profile" }, method = RequestMethod.GET)
    public ModelAndView profile(final HttpServletRequest req) {
        modelAndView = new ModelAndView("profile");

        defaultModelAndViewObjects(req,"Profile");
        return modelAndView;
    }

    @RequestMapping(value = { "/products" }, method = RequestMethod.GET)
    public ModelAndView products(final HttpServletRequest req) {
        modelAndView = new ModelAndView("products");

        defaultModelAndViewObjects(req,"YAMAZON");
        return modelAndView;
    }

    @RequestMapping(value = { "/productSearch" }, method = RequestMethod.GET)
    public ModelAndView productSearch(final HttpServletRequest req) {
        modelAndView = new ModelAndView("productSearch");

        defaultModelAndViewObjects(req,"Product Search");
        return modelAndView;
    }

    @RequestMapping(value = { "/productdetail/{productId}" }, method = RequestMethod.GET)
    public ModelAndView productdetail(final HttpServletRequest req, @PathVariable String productId) {
        modelAndView = new ModelAndView("productdetail");

        defaultModelAndViewObjects(req,"");
        return modelAndView;
    }

    @RequestMapping(value = { "/productFinder" }, method = RequestMethod.GET)
    public ModelAndView productFinder(final HttpServletRequest req) {
        modelAndView = new ModelAndView("productFinder");

        defaultModelAndViewObjects(req,"Find a Product in an actual physical store");
        return modelAndView;
    }


    @RequestMapping(value = { "/console" }, method = RequestMethod.GET)
    public ModelAndView console(final HttpServletRequest req) {
        modelAndView = new ModelAndView("console");

        defaultModelAndViewObjects(req,"Console");
        return modelAndView;
    }
}
