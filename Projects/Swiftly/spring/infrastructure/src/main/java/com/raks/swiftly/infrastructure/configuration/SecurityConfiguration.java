package com.raks.swiftly.infrastructure.configuration;

import com.raks.swiftly.infrastructure.configuration.jwt.CustomUserDetailsService;
import com.raks.swiftly.infrastructure.configuration.jwt.JwtAuthEntryPoint;
import com.raks.swiftly.infrastructure.configuration.jwt.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static com.raks.swiftly.domain.model.enums.Permission.*;
import static com.raks.swiftly.domain.model.enums.Role.*;
import static org.springframework.http.HttpMethod.*;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {

    private final JwtAuthEntryPoint        _jwtAuthEntryPoint;
    private final JwtAuthenticationFilter  _jwtAuthenticationFilter;
    private final CustomUserDetailsService _userDetailsService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
            .exceptionHandling().authenticationEntryPoint(_jwtAuthEntryPoint)
            .and()
            .authorizeHttpRequests()
            .requestMatchers(
                    "/api/v1/**",
                    "/admin/**",
                    "/css/**",
                    "/font/**",
                    "/img/**",
                    "/favicon.ico",
                    "/scripts/**"
            ).permitAll()

//            .requestMatchers("/api/v1/management/**").hasAnyRole(ADMIN.name(), CLIENT.name())
//
//            .requestMatchers(GET, "/api/v1/management/**").hasAnyAuthority(ADMIN_READ.name(), CLIENT_READ.name())
//            .requestMatchers(POST, "/api/v1/management/**").hasAnyAuthority(ADMIN_CREATE.name(), CLIENT_CREATE.name())
//            .requestMatchers(PUT, "/api/v1/management/**").hasAnyAuthority(ADMIN_UPDATE.name(), CLIENT_UPDATE.name())
//            .requestMatchers(DELETE, "/api/v1/management/**").hasAnyAuthority(ADMIN_DELETE.name(), CLIENT_DELETE.name())
//
//            .requestMatchers("/api/v1/admin/**").hasRole(ADMIN.name())
//
//            .requestMatchers(GET, "/api/v1/admin/**").hasAuthority(ADMIN_READ.name())
//            .requestMatchers(POST, "/api/v1/admin/**").hasAuthority(ADMIN_CREATE.name())
//            .requestMatchers(PUT, "/api/v1/admin/**").hasAuthority(ADMIN_UPDATE.name())
//            .requestMatchers(DELETE, "/api/v1/admin/**").hasAuthority(ADMIN_DELETE.name())

            .anyRequest().authenticated()
            .and()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .authenticationProvider(authenticationProvider());
//            .addFilterBefore(_jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
//            .logout()
//            .logoutUrl("/api/v1/auth/logout")
//            .logoutSuccessHandler((request, response, authentication) -> SecurityContextHolder.clearContext());

        return http.build();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(_userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}