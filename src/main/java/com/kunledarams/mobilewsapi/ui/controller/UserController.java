/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kunledarams.mobilewsapi.ui.controller;

import com.kunledarams.mobilewsapi.execptions.UserServiceExecption;
import com.kunledarams.mobilewsapi.io.entity.UserEntity;
import com.kunledarams.mobilewsapi.servicee.AddressService;
import com.kunledarams.mobilewsapi.servicee.UserService;
import com.kunledarams.mobilewsapi.shared.dto.AddressDto;
import com.kunledarams.mobilewsapi.shared.dto.UserDto;
import com.kunledarams.mobilewsapi.ui.model.request.UserDetailsRequestModel;
import com.kunledarams.mobilewsapi.ui.model.response.AddressRest;
import com.kunledarams.mobilewsapi.ui.model.response.ErrorMessages;
import com.kunledarams.mobilewsapi.ui.model.response.OperationName;
import com.kunledarams.mobilewsapi.ui.model.response.OperationStatus;
import com.kunledarams.mobilewsapi.ui.model.response.OperationStatusModel;
import com.kunledarams.mobilewsapi.ui.model.response.UserResponse;
import java.lang.reflect.Type;

import java.util.ArrayList;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author TremendocLimited
 */
@RestController
@RequestMapping("customer")

public class UserController {

    @Autowired
    UserService userService;
    
    @Autowired
    AddressService addressService;
//    ModelMapper modelMapper = new ModelMapper();
    @GetMapping(path = "/{id}")
    public UserResponse getUser(@PathVariable String id) {

        UserResponse returnValue = new UserResponse();
        UserDto userDto = userService.getUserByUserId(id);
        BeanUtils.copyProperties(userDto, returnValue);
        return returnValue;
    }

    @PostMapping
    public UserResponse createUser(@RequestBody UserDetailsRequestModel userDetails) throws Exception {

        UserResponse returnValue = new UserResponse();
        if (userDetails.getFirstName().isEmpty()) {
            throw new UserServiceExecption(ErrorMessages.MISSING_REEQUIRED_FIELD.getErrorMessage());
        }

//        UserDto userDto = new UserDto(); 
//        BeanUtils.copyProperties(userDetails, userDto);
        ModelMapper modelMapper = new ModelMapper();
        UserDto userDto = modelMapper.map(userDetails, UserDto.class);

        UserDto createdUser = userService.createUser(userDto);
        returnValue = modelMapper.map(createdUser, UserResponse.class);
        return returnValue;

    }

    @PutMapping(path = "/{id}")
    public UserResponse updateUser(@PathVariable String id, @RequestBody UserDetailsRequestModel userDetail) {

        UserResponse returnValue = new UserResponse();
        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(userDetail, userDto);

        UserDto updateUser = userService.updateUser(id, userDto);
        BeanUtils.copyProperties(updateUser, returnValue);

        return returnValue;
    }

    @DeleteMapping(path = "/{id}")
    public OperationStatusModel deleteUser(@PathVariable String id) {

        OperationStatusModel returnValue = new OperationStatusModel();

        returnValue.setOperationName(OperationName.DELETE.name());

        userService.delectUserById(id);

        returnValue.setOperationStatus(OperationStatus.SUCCESS.name());

        return returnValue;
    }

    @GetMapping
    public List<UserResponse> getUsers(@RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "limit", defaultValue = "25") int limit) {

        List<UserResponse> returnValue = new ArrayList<>();
        List<UserDto> users = userService.getUsers(page, limit);

        for (UserDto userDto : users) {
            UserResponse usersModel = new UserResponse();
            BeanUtils.copyProperties(userDto, usersModel);
            returnValue.add(usersModel);
        }

        return returnValue;

    }

    // http://localhost:8080/customer/userId/addressess
    @GetMapping(path = "/{id}/addresses")
    public List<AddressRest> getAddresses(@PathVariable String id) {

        List<AddressRest> returnValue = new ArrayList<>();
        List<AddressDto> addressDto = addressService.getAddress(id);

        if (addressDto != null && !addressDto.isEmpty()) {

            Type listType = new TypeToken<List<AddressRest>>() {
            }.getType();
            returnValue = new ModelMapper().map(addressDto, listType);
        }
        return returnValue;
    }
    @GetMapping(path = "/{userId}/addresses/{addressId}")
    public AddressRest getAddress(@PathVariable String addressId){
        AddressRest returnValue=null;
        
        AddressDto address = addressService.getAddressById(addressId);
        if(address!=null){
            returnValue= new ModelMapper().map(address, AddressRest.class);
        }
        return returnValue;
    }
    

}
