/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gym.gym_management.repository;

import com.gym.gym_management.enums.AttandanceStatus;
import com.gym.gym_management.model.AttandanceModel;
import com.gym.gym_management.model.UserModel;
import java.sql.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author tusha
 */
@Repository
public interface AttandanceRepo extends JpaRepository<AttandanceModel, Integer>{
    List<AttandanceModel> findByDateAndAttandanceStatus(Date now , AttandanceStatus status);
    List<AttandanceModel> findByDateAndUser(Date date , UserModel user);
    
    @Query(value = "SELECT * FROM attandance_data WHERE date >=:now_date AND date <=:end_date AND user_id =:userId" , nativeQuery = true)
    List<AttandanceModel> findByDateAndUser(@Param("now_date")Date now ,@Param("end_date")Date end ,@Param("userId") int user);
    
    
    //List<AttandanceModel> findByUser(@Param("userId") int user);
    
    public boolean findExistByUser(int id);
    
    @Query(value = "SELECT * FROM attandance_data WHERE user_id =:userId" , nativeQuery = true)
    public List<AttandanceModel> findByUser(@Param("userId")int userId);
}
