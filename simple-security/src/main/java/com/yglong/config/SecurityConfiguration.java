package com.yglong.config;

import com.yglong.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    private BackdoorAuthenticationProvider backdoorAuthenticationProvider;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Autowired
    private RoleBasedMetadataSource roleBasedMetadataSource;

    @Autowired
    private RoleBasedAccessDecisionManager roleBasedAccessDecisionManager;

    @Autowired
    private CustomAccessDeniedHandler customAccessDeniedHandler;

    /**
     * 配置如何验证用户
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().passwordEncoder(passwordEncoder())
                .withUser("user")
                .password(passwordEncoder().encode("pwd"))
                .roles("USER")
                .and()
                .withUser("admin")
                .password(passwordEncoder().encode("pwd"))
                .roles("USER", "ADMIN");
        auth.authenticationProvider(backdoorAuthenticationProvider);
        auth.userDetailsService(customUserDetailsService);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/index.html",
                "/static/**", "/favicon.ico",
                "/error", "/login_p", "/default/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
                    @Override
                    public <O extends FilterSecurityInterceptor> O postProcess(O object) {
                        object.setSecurityMetadataSource(roleBasedMetadataSource);
                        object.setAccessDecisionManager(roleBasedAccessDecisionManager);
                        return object;
                    }
                })
                .and()
                .formLogin().loginPage("/login_p").loginProcessingUrl("/login").permitAll()
                .usernameParameter("myusername").passwordParameter("mypassword")
                .and()
                .logout().logoutUrl("/logout").logoutSuccessUrl("/login").permitAll()
                .and()
                .csrf().disable()
                .exceptionHandling().accessDeniedHandler(customAccessDeniedHandler);
//                .antMatchers("/", "/index", "/error", "/default").permitAll()
//                .antMatchers("/user/**").hasRole("USER")
//                .antMatchers("/admin/**").hasRole("ADMIN")
//                .and()
//                .formLogin().loginPage("/login").defaultSuccessUrl("/user")
//                .usernameParameter("myusername").passwordParameter("mypassword")
//                .and()
//                .logout().logoutUrl("/logout").logoutSuccessUrl("/login");
    }
}
