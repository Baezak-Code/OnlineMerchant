package com.online.merchant.model.config;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public abstract class BaseAuthenticationProperties {

    private String username;
    private String password;
    private String authorities;
}
