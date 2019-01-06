package com.graphstory.util;

import org.apache.commons.lang3.Validate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public abstract class SessionUtils {

    public SessionUtils() {
    }

    protected static HttpSession getSession(HttpServletRequest req) {
        return req.getSession(true);
    }

    public static void set(HttpServletRequest req, String name, Object value) {
        Validate.notNull(req);
        Validate.notNull(name);
        getSession(req).setAttribute(name, value);
    }

    public static Object get(HttpServletRequest req, String name) {
        Validate.notNull(req);
        Validate.notNull(name);
        return getSession(req).getAttribute(name);
    }

    public static Object remove(HttpServletRequest req, String name) {
        Validate.notNull(req);
        Validate.notNull(name);
        Object value = get(req, name);
        getSession(req).removeAttribute(name);
        return value;
    }
}