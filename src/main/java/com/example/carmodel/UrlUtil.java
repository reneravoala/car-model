package com.example.carmodel;

import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;

public class UrlUtil {

    public static final String baseUrl =
            ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString();
    /**
     * This method to get base URL of the application
     * @param request the HttpServletRequest object
     * @return the base URL
     */
    public static String getBaseUrl(HttpServletRequest request) {

        return ServletUriComponentsBuilder
                .fromRequestUri(request)
                .replacePath(null)
                .build()
                .toUriString();
    }
}