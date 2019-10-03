/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kunledarams.mobilewsapi.ui.model.request;

import java.util.List;

/**
 *
 * @author TremendocLimited
 */
public class UserDetailsRequestModel {

    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String password;
    private List<AddressRequestModel> address;

   

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<AddressRequestModel> getAddress() {
        return address;
    }

    public void setAddress(List<AddressRequestModel> address) {
        this.address = address;
    }

   
}
