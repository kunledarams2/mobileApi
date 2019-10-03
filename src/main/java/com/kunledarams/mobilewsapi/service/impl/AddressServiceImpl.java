/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kunledarams.mobilewsapi.service.impl;

import com.kunledarams.mobilewsapi.io.entity.AddressEntity;
import com.kunledarams.mobilewsapi.io.entity.UserEntity;
import com.kunledarams.mobilewsapi.io.repositories.AddressRepository;
import com.kunledarams.mobilewsapi.io.repositories.UserRepository;
import com.kunledarams.mobilewsapi.servicee.AddressService;
import com.kunledarams.mobilewsapi.shared.dto.AddressDto;
import java.util.ArrayList;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author TremendocLimited
 */
@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    AddressRepository addressRepository;

    @Override
    public List<AddressDto> getAddress(String userId) {

        List<AddressDto> returnValue = new ArrayList<>();
        UserEntity userEntity = userRepository.findByUserId(userId);

        if (userEntity == null) {
            return returnValue;
        }

        Iterable<AddressEntity> address = addressRepository.findAllByUserDetails(userEntity);
        
        for( AddressEntity addressEntity: address ){
            returnValue.add(new ModelMapper().map(addressEntity, AddressDto.class)); 
        }
        
        return returnValue;
    }

    @Override
    public AddressDto getAddressById(String addressId) {
        AddressDto returnValue = new AddressDto();
        
        AddressEntity addressEntity= addressRepository.findByAddressId(addressId);
        if(addressEntity !=null){
            returnValue= new ModelMapper().map(addressEntity, AddressDto.class);
        }
        return returnValue;
    }

}
