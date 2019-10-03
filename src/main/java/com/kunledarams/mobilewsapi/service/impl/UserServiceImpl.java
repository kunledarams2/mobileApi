/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kunledarams.mobilewsapi.service.impl;

import com.kunledarams.mobilewsapi.io.repositories.UserRepository;
import com.kunledarams.mobilewsapi.execptions.UserServiceExecption;
import com.kunledarams.mobilewsapi.io.entity.UserEntity;
import com.kunledarams.mobilewsapi.servicee.UserService;
import com.kunledarams.mobilewsapi.shared.dto.AddressDto;
import com.kunledarams.mobilewsapi.shared.dto.UserDto;
import com.kunledarams.mobilewsapi.shared.utils.Utils;
import com.kunledarams.mobilewsapi.ui.model.response.ErrorMessages;

import java.util.ArrayList;
import java.util.List;
import org.modelmapper.ModelMapper;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

/**
 *
 * @author TremendocLimited
 */
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	Utils utils;

	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public UserDto createUser(UserDto user) {

		if (userRepository.findByEmail(user.getEmail()) != null) {
			throw new RuntimeException("Record already exist");
		}
                for(int i=0; i<user.getAddress().size(); i++){
                    AddressDto address = user.getAddress().get(i);
                    address.setUserDetails(user);
                    address.setAddressId(utils.getAddressId(20));
                    user.getAddress().set(i, address);
                }
		String publicUserId = utils.getUserId(30);
//		UserEntity userEntity = new UserEntity();

                ModelMapper modelMapper = new ModelMapper();
                UserEntity userEntity= modelMapper.map(user, UserEntity.class);
                
//		BeanUtils.copyProperties(user, userEntity);

		userEntity.setUserId(publicUserId);
		userEntity.setEncryptedPassword(bCryptPasswordEncoder.encode(user.getPassword()));

		UserEntity storedUserDetails = userRepository.save(userEntity);

                 UserDto returnValue=modelMapper.map(storedUserDetails, UserDto.class);
//		BeanUtils.copyProperties(storedUserDetails, returnValue);
		return returnValue;
	}

	@Override
	public UserDto getUser(String email) {
		UserEntity userEntity = userRepository.findByEmail(email);

		if (userEntity == null) {
			throw new UsernameNotFoundException(email);
		}
		UserDto returnValue = new UserDto();
		BeanUtils.copyProperties(userEntity, returnValue);

		return returnValue;

	}

	@Override
	public UserDto getUserByUserId(String userId) {

		UserEntity userEntity = userRepository.findByUserId(userId);
		UserDto returnValue = new UserDto();

		if (userEntity == null)
			throw new UsernameNotFoundException("User with Id: " + userId + " is not found");

		BeanUtils.copyProperties(userEntity, returnValue);
		return returnValue;
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		UserEntity userEntity = userRepository.findByEmail(email);

		if (userEntity == null) {
			throw new UsernameNotFoundException(email);
		}
		return new User(userEntity.getEmail(), userEntity.getEncryptedPassword(), new ArrayList<>());

	}

	@Override
	public UserDto updateUser(String userId, UserDto user) {

		UserEntity userEntity = userRepository.findByUserId(userId);

		UserDto returnValue = new UserDto();
		if (userEntity == null)
			throw new UserServiceExecption(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());

		userEntity.setFirstName(user.getFirstName());
		userEntity.setLastName(user.getLastName());
		UserEntity updateUserDetails = userRepository.save(userEntity);
		
		BeanUtils.copyProperties(updateUserDetails, returnValue);

		return returnValue;
	}

	@Override
	public void  delectUserById(String userId) {
		
		UserEntity userEntity = userRepository.findByUserId(userId);
		
		if(userEntity==null) throw new UserServiceExecption(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());
		userRepository.delete(userEntity);
		
	}

	@Override
	public List<UserDto> getUsers(int page, int limit) {
		List<UserDto> returnValue = new ArrayList<>();
		
		if(page>0) page-=1;
		
		Pageable pageableRequest= PageRequest.of(page, limit);
		
		Page<UserEntity> usersPage= userRepository.findAll(pageableRequest);
		List<UserEntity> users = usersPage.getContent();
		
		for(UserEntity userEntity:users) {
			UserDto userDto = new UserDto();
			BeanUtils.copyProperties(userEntity, userDto);
			returnValue.add(userDto);
		}
		
		return returnValue;
		}

}
