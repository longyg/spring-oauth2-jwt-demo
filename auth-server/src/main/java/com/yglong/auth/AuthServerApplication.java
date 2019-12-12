package com.yglong.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableOAuth2Client
@RestController
public class AuthServerApplication extends WebSecurityConfigurerAdapter {

    public static void main(String[] args ) {
        SpringApplication.run(AuthServerApplication.class, args);
    }
}
