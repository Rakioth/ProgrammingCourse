package com.raks.swiftly.application.util;

import jakarta.servlet.http.HttpSession;
import org.apache.commons.beanutils.BeanUtilsBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;

@Component
public class SessionManager {

    private static HttpSession   _session;
    private static BeanUtilsBean _nullAwareMerge;

    @Autowired
    public SessionManager(HttpSession session) {
        _session        = session;
        _nullAwareMerge = new NullAwareMerge();
    }

    public static void create(String name, Object value) {
        _session.setAttribute(name, value);
    }

    public static Object read(String name) {
        return _session.getAttribute(name);
    }

    public static void update(String name, Object value) {
        try {
            Object attribute = _session.getAttribute(name);
            _nullAwareMerge.copyProperties(attribute, value);
            _session.setAttribute(name, attribute);
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    public static void delete(String name) {
        _session.removeAttribute(name);
    }

}