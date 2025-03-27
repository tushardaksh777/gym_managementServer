/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gym.gym_management.repository;

import com.gym.gym_management.model.ExpensesModel;
import com.gym.gym_management.model.TransactionModel;
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
public interface TransactionRepo extends JpaRepository<TransactionModel, Integer>{
    public List<TransactionModel> findByUser(UserModel userId);
    @Query(value = "SELECT * FROM transaction_model WHERE date >=:now_date AND date <=:end_date" , nativeQuery = true)
    public List<TransactionModel> findByDate(@Param("now_date")Date start ,@Param("end_date")Date end);
}
