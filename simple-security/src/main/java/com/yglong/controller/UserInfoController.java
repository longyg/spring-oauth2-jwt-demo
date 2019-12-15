package com.yglong.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class UserInfoController {

    @RequestMapping(value = "/me")
    public Principal user(Principal principal) {
        return principal;
    }
}
