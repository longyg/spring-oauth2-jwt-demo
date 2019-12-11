package com.yglong.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    @RequestMapping({"/", "/index"})
    public String index(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            model.addAttribute("authorities", authentication.getAuthorities().toString());
            model.addAttribute("username", authentication.getName());
        }
        return "index";
    }

    @RequestMapping("/login")
    public String login(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            model.addAttribute("authorities", authentication.getAuthorities().toString());
            model.addAttribute("username", authentication.getName());
        }
        return "login";
    }

    @RequestMapping("/login_p")
    public String loginP(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            model.addAttribute("authorities", authentication.getAuthorities().toString());
            model.addAttribute("username", authentication.getName());
        }
        return "login_p";
    }
}
