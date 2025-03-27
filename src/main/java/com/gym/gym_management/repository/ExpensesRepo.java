/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gym.gym_management.repository;
import com.gym.gym_management.enums.Expenses;
import org.springframework.stereotype.Repository;

import com.gym.gym_management.model.ExpensesModel;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author tusha
 */
@Repository
public interface ExpensesRepo extends JpaRepository<ExpensesModel , Integer>{
    List<ExpensesModel> findByExpenses(Expenses expense);
    @Query(value = "SELECT * FROM expenses WHERE date >=:now_date AND date <=:end_date" , nativeQuery = true)
    List<ExpensesModel> findByDate(@Param("now_date")Date start ,@Param("end_date")Date end);
}
