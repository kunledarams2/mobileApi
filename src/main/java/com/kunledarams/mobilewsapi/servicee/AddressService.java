/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kunledarams.mobilewsapi.servicee;

import com.kunledarams.mobilewsapi.shared.dto.AddressDto;
import java.util.List;

/**
 *
 * @author TremendocLimited
 */
public interface AddressService {
    List<AddressDto> getAddress(String userId);
   AddressDto getAddressById(String addressId);
}
