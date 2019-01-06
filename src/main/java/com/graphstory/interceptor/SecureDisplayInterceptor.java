package com.graphstory.interceptor;

import com.graphstory.util.GraphStoryConstants;
import com.graphstory.util.SessionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SecureDisplayInterceptor implements HandlerInterceptor {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public boolean preHandle(HttpServletRequest req, HttpServletResponse res, Object o) throws Exception {
        String userId = (String) SessionUtils.get(req, GraphStoryConstants.userId);

        if (userId == null) {

            res.sendRedirect("/");
            logger.warn("ILLEGAL ACTIVTY WOOOT WOOOT WOOOOT MEE-MUUR MEE-MUUR MEE-MUUR");
            return false;
        }else{
            logger.info(userId);
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
