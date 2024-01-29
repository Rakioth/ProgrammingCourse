package com.raks.swiftly.infrastructure.configuration;

import com.raks.swiftly.application.util.CookieManager;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component
public class AuthConfiguration implements HandlerInterceptor {

    private Boolean isAuthenticated;

    @Override
    public boolean preHandle(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull Object handler) {
        String accessToken = CookieManager.read("access_token");
        isAuthenticated = (accessToken != null);
        return true;
    }

    @Override
    public void postHandle(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull Object handler, ModelAndView modelAndView) {
        if (!isAuthenticated)
            modelAndView.setViewName("forward:/admin/login");
    }

}