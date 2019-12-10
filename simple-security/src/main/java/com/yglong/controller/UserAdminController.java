package com.yglong.controller;

import com.yglong.model.Role;
import com.yglong.model.User;
import com.yglong.model.UserRoles;
import com.yglong.service.RoleService;
import com.yglong.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/default")
public class UserAdminController {
    @Autowired
    private RoleService roleService;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/role")
    public void addDefaultRoles() {
        List<Role> roles = new ArrayList<>();
        roles.add(new Role("ADMIN"));
        roles.add(new Role("USER"));
        roles.add(new Role("NORMAL"));
        roleService.addRoles(roles);
    }

    @RequestMapping(value = "/user")
    public void addDefaultUsers() {
        List<User> users = new ArrayList<>();
        users.add(new User("Long Yonggang", "Sichuan", "yglong", "$2a$10$PPtqrkVphgP/V0QuaApG6eSZf5ucYo2nE/LgF2cerQAs9q/x7j7Dy"));
        users.add(new User("Administrator", "Chengdu", "admin", "$2a$10$PPtqrkVphgP/V0QuaApG6eSZf5ucYo2nE/LgF2cerQAs9q/x7j7Dy"));
        users.add(new User("Normal", "Sichuan", "normal", "$2a$10$PPtqrkVphgP/V0QuaApG6eSZf5ucYo2nE/LgF2cerQAs9q/x7j7Dy"));
        userService.addUsers(users);
    }

    @RequestMapping(value = "/userRoles")
    public void addDefaultUserRoles() {
        List<UserRoles> userRoles = new ArrayList<>();
        userRoles.add(new UserRoles("yglong", "USER,ADMIN,NORMAL"));
        userRoles.add(new UserRoles("admin", "ADMIN"));
        userRoles.add(new UserRoles("normal", "NORMAL"));
        userService.addUserRoles(userRoles);
    }

    @RequestMapping(value = "/userAndRoles")
    public void addDefaultUserRoles1() {
        List<User> users = new ArrayList<>();
        users.add(new User("Long Yonggang", "Sichuan", "yglong", "$2a$10$PPtqrkVphgP/V0QuaApG6eSZf5ucYo2nE/LgF2cerQAs9q/x7j7Dy", "ADMIN, USER, NORMAL"));
        users.add(new User("Administrator", "Chengdu", "admin", "$2a$10$PPtqrkVphgP/V0QuaApG6eSZf5ucYo2nE/LgF2cerQAs9q/x7j7Dy", "ADMIN"));
        users.add(new User("Normal", "Sichuan", "normal", "$2a$10$PPtqrkVphgP/V0QuaApG6eSZf5ucYo2nE/LgF2cerQAs9q/x7j7Dy", "NORMAL"));
        List<Role> allRoles = new ArrayList<>();
        for (User user : users) {
            for (Role role : user.getRoles()) {
                if (!allRoles.contains(role)) {
                    allRoles.add(role);
                }
            }
        }
        roleService.addRoles(allRoles);
        userService.addUserWithRoles(users);
    }

    @RequestMapping(value = "/getUser/{name}")
    public User getUser(@PathVariable String name) {
        return userService.getUser(name);
    }
}
