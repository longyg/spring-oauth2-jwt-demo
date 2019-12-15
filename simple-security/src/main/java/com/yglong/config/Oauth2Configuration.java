package com.yglong.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;

//@Configuration
//@EnableAuthorizationServer
public class Oauth2Configuration extends AuthorizationServerConfigurerAdapter {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
                .withClient("demo-client")
                .secret(passwordEncoder.encode("demo-client"))
                .redirectUris("http://www.baidu.com")
                .authorizedGrantTypes("refresh_token", "password", "client_credentials", "authorization_code")
                .scopes("all");
    }
}
