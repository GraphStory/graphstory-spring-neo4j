package com.graphstory.interceptor;

import com.graphstory.util.GraphStoryConstants;
import com.graphstory.util.SessionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;

public class DisplayInterceptor implements HandlerInterceptor {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public boolean preHandle(HttpServletRequest req, HttpServletResponse res, Object o) throws Exception {
        Enumeration<String> headerNames = req.getHeaderNames();

        if (headerNames != null) {
            while (headerNames.hasMoreElements()) {
                logger.info("" + req.getHeader(headerNames.nextElement()));
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

        String userId = (String) SessionUtils.get(httpServletRequest, GraphStoryConstants.userId);

        if (userId!= null) {
            modelAndView.addObject("isLoggedIn", true);
        }

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
    }
}
