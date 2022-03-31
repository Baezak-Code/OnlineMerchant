package com.online.merchant.model.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "authentication")
public class AuthenticationProperties extends BaseAuthenticationProperties {
}
