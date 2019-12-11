package com.yglong.config;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Iterator;

@Component
public class RoleBasedAccessDecisionManager implements AccessDecisionManager {

    /**
     * 权限判定方法。基于角色判定：当前登录用户是否具有访问某资源的权限
     * @param authentication 包含了用户及角色信息
     * @param o 所访问的资源
     * @param roles 访问资源所需要的角色，来自RoleBasedMetadataSource的getAttributes
     * @throws AccessDeniedException
     * @throws InsufficientAuthenticationException
     */
    @Override
    public void decide(Authentication authentication,
                       Object o, Collection<ConfigAttribute> roles)
            throws AccessDeniedException, InsufficientAuthenticationException {
        // 获取当前用户所具有的角色
        Collection<? extends GrantedAuthority> userRoles = authentication.getAuthorities();

        // 循环资源所需要的角色
        Iterator<ConfigAttribute> iterator = roles.iterator();
        while (iterator.hasNext()) {
            ConfigAttribute role = iterator.next();
            String roleName = role.getAttribute();

            if ("ROLE_NONE".equals(roleName)) {
                if (authentication instanceof AnonymousAuthenticationToken) {
                    throw new BadCredentialsException("User does not login");
                } else {
                    return;
                }
            }

            for (GrantedAuthority userRole : userRoles) {
                String userRoleName = userRole.getAuthority();
                if (userRoleName.equals("ROLE_ADMIN")) {
                    return; // 用户具有ROLE_ADMIN权限，则可以访问所有资源
                }
                if (userRoleName.equals(roleName)) {
                    return; // 匹配成功就直接返回
                }
            }
        }
        // 不能完成匹配
        throw new AccessDeniedException("You don't have permission to access: " + o);
    }

    @Override
    public boolean supports(ConfigAttribute configAttribute) {
        return true;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
