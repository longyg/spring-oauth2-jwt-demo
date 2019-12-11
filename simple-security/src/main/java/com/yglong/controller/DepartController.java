package com.yglong.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.Collection;

@Controller
public class DepartController {
    @RequestMapping("/depart1")
    public String depart1(@AuthenticationPrincipal Principal principal, Model model) {
        model.addAttribute("username", principal.getName());
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("authorities", auth.getAuthorities().toString());
        return "depart1/depart1";
    }

    @RequestMapping("/depart2")
    public String depart2(@AuthenticationPrincipal Principal principal, Model model) {
        model.addAttribute("username", principal.getName());
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("authorities", auth.getAuthorities().toString());
        return "depart2/depart2";
    }
}
