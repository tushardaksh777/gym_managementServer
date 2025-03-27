/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gym.gym_management.repository;

import com.gym.gym_management.enums.UserStatus;
import com.gym.gym_management.model.UserModel;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author tusha
 */
@Repository
public interface UserRepo extends JpaRepository<UserModel , Integer>{
    List<UserModel> findByStatus(UserStatus status);
    UserModel findByRegistrationNo(int registrationNo);
    List<UserModel> findByIdNotIn(List<Integer> ids);
    
}
