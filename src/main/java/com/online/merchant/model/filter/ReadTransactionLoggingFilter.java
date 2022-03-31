package com.online.merchant.model.filter;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class ReadTransactionLoggingFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        final HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        log.info("Read Transaction Request - Method: {} | URI: {}", httpServletRequest.getMethod(), httpServletRequest.getRequestURI());
        chain.doFilter(request, response);
        final HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        log.info("Read Transaction Response - Content Type: {} | HTTP Status Code: {} | Date: {}",
                httpServletResponse.getContentType(), httpServletResponse.getStatus(), httpServletResponse.getHeader("Date"));
    }
}
