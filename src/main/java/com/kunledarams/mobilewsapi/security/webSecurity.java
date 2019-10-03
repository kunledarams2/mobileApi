/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kunledarams.mobilewsapi.security;

import com.kunledarams.mobilewsapi.servicee.UserService;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 *
 * @author TremendocLimited
 */
@EnableWebSecurity
public class webSecurity extends WebSecurityConfigurerAdapter{
    
    private UserService userDetailsService;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public webSecurity(UserService userDetailsService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userDetailsService = userDetailsService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf()
                .disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, SecurityConstant.SIGN_UP_URL)
                .permitAll()
                .anyRequest()
                .authenticated().and()
                .addFilter(getAuthicationUrl()) // login authentication
             
                .addFilter(new AuthorizationFliter(authenticationManager()))  // authorization given to other end point
                .sessionManagement()   
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
    }
    
    /* create url for authentication */
    public AuthenticationFliter getAuthicationUrl() throws Exception{
       final AuthenticationFliter fliter = new AuthenticationFliter(authenticationManager());
        fliter.setFilterProcessesUrl("/customer/login");
        return fliter;
    }
    
}
