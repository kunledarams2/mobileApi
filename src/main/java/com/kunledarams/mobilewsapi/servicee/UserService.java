/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kunledarams.mobilewsapi.servicee;

import com.kunledarams.mobilewsapi.shared.dto.UserDto;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 *
 * @author TremendocLimited
 */
public interface UserService extends UserDetailsService{
    
   UserDto createUser(UserDto user);
   UserDto getUser(String email);
   UserDto getUserByUserId(String userId); 
   UserDto updateUser(String userId, UserDto user);
   void delectUserById(String userId);
   
   List<UserDto>getUsers(int page, int limit);
}
