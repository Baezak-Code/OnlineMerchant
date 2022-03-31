package com.online.merchant.model.config;

import com.online.merchant.model.filter.ReadTransactionLoggingFilter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean<ReadTransactionLoggingFilter> loggingFilter(@Value("${read.transaction.url.pattern}") final String urlPattern){
        final FilterRegistrationBean<ReadTransactionLoggingFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new ReadTransactionLoggingFilter());
        registrationBean.addUrlPatterns(urlPattern);
        registrationBean.setOrder(2);
        return registrationBean;
    }
}
