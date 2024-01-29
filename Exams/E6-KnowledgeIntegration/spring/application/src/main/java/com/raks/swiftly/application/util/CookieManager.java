package com.raks.swiftly.application.util;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

@Component
public class CookieManager {

    private static HttpServletRequest  _request;
    private static HttpServletResponse _response;

    @Autowired
    public CookieManager(HttpServletRequest request, HttpServletResponse response) {
        _request  = request;
        _response = response;
    }

    public static void create(String name, String value) {
        Cookie cookie = new Cookie(name, value);
        cookie.setPath("/");
        cookie.setMaxAge(604800);
        _response.addCookie(cookie);
    }

    public static String read(String name) {
        Cookie cookie = getCookie(name);
        return cookie != null ? cookie.getValue() : null;
    }

    public static void delete(String name) {
        Cookie cookie = new Cookie(name, null);
        cookie.setMaxAge(0);
        _response.addCookie(cookie);
    }

    public static void logUsername(String loggedUsers, String username) {
        String log = null;

        if (loggedUsers.equals("empty"))
            log = username;
        else if (!readLog(loggedUsers, username))
            log = String.format("%s~%s", loggedUsers, username);

        create("_luser", log);
    }

    private static boolean readLog(String loggedUsers, String username) {
        return Arrays.asList(loggedUsers.split("~"))
                     .contains(username);
    }

    private static Cookie getCookie(String name) {
        if (_request.getCookies() == null) return null;
        return Arrays.stream(_request.getCookies())
                     .filter(cookie -> cookie.getName().equals(name))
                     .findFirst()
                     .orElse(null);
    }

}