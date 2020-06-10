
package com.kunledarams.mobilewsapi.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kunledarams.mobilewsapi.SpringApplicationContext;
import com.kunledarams.mobilewsapi.servicee.UserService;
import com.kunledarams.mobilewsapi.shared.dto.UserDto;
import com.kunledarams.mobilewsapi.ui.model.request.UserLoginRequestModel;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 *
 * @author TremendocLimited
 */
public class AuthenticationFliter extends UsernamePasswordAuthenticationFilter {

    private AuthenticationManager authenticationManager;

    public AuthenticationFliter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

        try {
             UserLoginRequestModel cred = new ObjectMapper()
                    .readValue(request.getInputStream(),
                            UserLoginRequestModel.class);

            return authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            cred.getEmail(),
                            cred.getPassword(),
                            new ArrayList<>()));

        } catch (IOException exception) {
            throw new RuntimeException();
        }

    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request,
            HttpServletResponse response,
            FilterChain chain,
            Authentication authResult) throws IOException,
            ServletException {
        String userName= ((User)authResult.getPrincipal()).getUsername();
        
        String token = Jwts.builder()
                .setSubject(userName)
                .setExpiration(new Date(System.currentTimeMillis() + SecurityConstant.EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, SecurityConstant.getTokenSecret())
                .compact();
        /*  
        getUser public id 
        */
        UserService userService = (UserService)SpringApplicationContext.getBean("userServiceImpl");
        UserDto userDto= userService.getUser(userName);
        userDto.getUserId(); 
        
        
       response.addHeader(SecurityConstant.HEADER_STRING, SecurityConstant.TOKEN_PREFIX + token);
       response.addHeader("UserID", userDto.getUserId());
//        super.successfulAuthentication(request, response, chain, authResult); //To change body of generated methods, choose Tools | Templates.
    }

}
