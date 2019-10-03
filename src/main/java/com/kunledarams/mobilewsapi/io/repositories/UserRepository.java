/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kunledarams.mobilewsapi.io.repositories;

import com.kunledarams.mobilewsapi.io.entity.UserEntity;
import org.springframework.stereotype.Repository;
//import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
//import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

@Repository
public interface UserRepository extends PagingAndSortingRepository<UserEntity, Long>{
    
   UserEntity findByEmail(String email);
   UserEntity findByUserId(String userId);
    
}
