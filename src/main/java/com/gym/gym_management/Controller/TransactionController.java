/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gym.gym_management.Controller;

import com.gym.gym_management.enums.PaymentMode;
import com.gym.gym_management.model.AttandanceModel;
import com.gym.gym_management.model.TransactionModel;
import com.gym.gym_management.model.UserModel;
import com.gym.gym_management.repository.TransactionRepo;
import com.gym.gym_management.repository.UserRepo;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import org.hibernate.mapping.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author tusha
 */
@RestController
@RequestMapping("/Transaction")
public class TransactionController {
    
    @Autowired
    TransactionRepo transactionRepo;
    
    @Autowired
    UserRepo userRepo;
    
    @PostMapping("/feeSubmit")
    public boolean submitFee(int registrationNo ,int registrationFee , int monthlyFee , int trademilFee , int lightsCharges , int personalTraining , int otherFee , String note ,String mode , String date){
        try {
            

        UserModel user = userRepo.findByRegistrationNo(registrationNo);
        
        Date feeSubmitDate = user.getFeeSubmitDate();
        
        LocalDate s = feeSubmitDate.toLocalDate();
        System.out.println("Before fee submit Date "+ feeSubmitDate.toString() + " "+s.getYear());
        Month month = Month.of(s.getMonth().getValue());
        int monthvalue = s.getMonth().getValue();
        int year = s.getYear();
        System.out.println("month "+ monthvalue + " year "+ year);
        if(monthvalue == 12){
            monthvalue = 1;
            month = Month.JANUARY;
            year=year+1;
        }else{
            monthvalue = monthvalue +1;
        }
        
        System.out.println("month "+ monthvalue + " year "+ year);
        
        Date nextFeeSubmitDate = Date.valueOf(s.plusMonths(1));
        
       System.out.println("After fee submit Date "+ nextFeeSubmitDate.toString());
//        Timestamp nextFeeSubmitDate = Timestamp.valueOf(f);
        user.setFeeSubmitDate(nextFeeSubmitDate);
        userRepo.save(user);
        TransactionModel tr = new TransactionModel();
        tr.setRegistrationfee(registrationFee);
        tr.setMonthlyFee(monthlyFee);
        tr.setTrademilFee(trademilFee);
        tr.setLightCharges(lightsCharges);
        tr.setPersonalTraining(personalTraining);
        tr.setOtherFee(otherFee);
        tr.setFeeNote(note);
        int amount = registrationFee + monthlyFee +trademilFee + lightsCharges + personalTraining + otherFee;
        tr.setAmount(amount);
        tr.setDate(Date.valueOf(LocalDate.parse(date)));
        tr.setUser(user);
        if(mode.equals("online" )){
            tr.setPaymentMode(PaymentMode.online);
        }else{
            tr.setPaymentMode(PaymentMode.offline);
        }
        
        transactionRepo.save(tr);
        
        return true;
        } catch (Exception e) {
            System.out.println("com.gym.gym_management.Controller.TransactionController.submitFee() "+ e.getMessage());
            return false;
        }
       
        
    }
    
        @PostMapping("/editUserfee")
    public boolean editFee(int id ,int registrationFee , int monthlyFee , int trademilFee , int lightsCharges , int personalTraining , int otherFee , String note ,String mode , String date){
        try {
        TransactionModel tr = transactionRepo.findById(id).get();
        
//        Date feeSubmitDate = user.getFeeSubmitDate();
//        System.out.println("Before fee submit Date "+ feeSubmitDate.toString());
//        LocalDate s = feeSubmitDate.toLocalDate();
//        
//        Date nextFeeSubmitDate = Date.valueOf(LocalDate.of(s.getYear(),Month.of(s.getMonth().getValue()+1) , s.getDayOfMonth()));
//        
//       System.out.println("After fee submit Date "+ nextFeeSubmitDate.toString());
////        Timestamp nextFeeSubmitDate = Timestamp.valueOf(f);
//        user.setFeeSubmitDate(nextFeeSubmitDate);
//        userRepo.save(user);
        //TransactionModel tr = new TransactionModel();
        tr.setRegistrationfee(registrationFee);
        tr.setMonthlyFee(monthlyFee);
        tr.setTrademilFee(trademilFee);
        tr.setLightCharges(lightsCharges);
        tr.setPersonalTraining(personalTraining);
        tr.setOtherFee(otherFee);
        tr.setFeeNote(note);
        int amount = registrationFee + monthlyFee +trademilFee + lightsCharges + personalTraining + otherFee;
        tr.setAmount(amount);
        tr.setDate(Date.valueOf(LocalDate.parse(date)));
        //tr.setUser(user);
        if(mode.equals("online" )){
            tr.setPaymentMode(PaymentMode.online);
        }else{
            tr.setPaymentMode(PaymentMode.offline);
        }
        
        transactionRepo.save(tr);
        
        return true;
        } catch (Exception e) {
            System.out.println("com.gym.gym_management.Controller.TransactionController.editFee() "+e.getMessage());
            return false;
        }
       
        
    }
    
    @PostMapping("/deletefee")
    public boolean deleteTransaction(int id){
        try {
            TransactionModel tr = transactionRepo.findById(id).get();
          
            UserModel user = tr.getUser();
        Date feeSubmitDate = user.getFeeSubmitDate();
        System.out.println("Before fee submit Date "+ feeSubmitDate.toString());
        LocalDate s = feeSubmitDate.toLocalDate();
        //System.out.println("submit Date "+ Month.of(s.getMonth().getValue()-1));
        Date nextFeeSubmitDate = Date.valueOf(LocalDate.of(s.getYear(),s.getMonth(), s.getDayOfMonth()).minusMonths(1));
        
       System.out.println("After fee submit Date "+ nextFeeSubmitDate.toString());
//        Timestamp nextFeeSubmitDate = Timestamp.valueOf(f);
        user.setFeeSubmitDate(nextFeeSubmitDate);
            userRepo.save(user);
            tr.setUser(null);
            transactionRepo.save(tr);
              transactionRepo.deleteById(tr.getId());
            return true;
        } catch (Exception e) {
            System.out.println("com.gym.gym_management.Controller.TransactionController.deleteTransaction() "+e.getMessage());
            return false;
        }
    }

    
    @PostMapping("/getUserfeesDetails")
    public List<TransactionModel> getUserFeeDetails(int registrationNo){
        try {
              UserModel user = userRepo.findByRegistrationNo(registrationNo);
       List<TransactionModel> transactions = new LinkedList<TransactionModel>();
        transactions =transactionRepo.findByUser(user);
       
       return transactions;
        } catch (Exception e) {
            return new LinkedList<>();
        }
    }
    
    @PostMapping("/getMonthlyTransaction")
    public List<TransactionModel> getMonthlyTransaction(String date){
        List<TransactionModel> transaction = new LinkedList<>();
        
        try {
        
            
        Date now =  Date.valueOf(LocalDate.parse(date));  
        LocalDateTime n = LocalDateTime.of(now.toLocalDate().getYear(), now.toLocalDate().getMonth(),1, 0,0);
        Date nowdate = Date.valueOf(n.toLocalDate());
        //LocalDateTime.of(0, Month.MARCH, 0, 0, 0);
        LocalDateTime l = LocalDateTime.of(now.toLocalDate().getYear(), now.toLocalDate().getMonth(), now.toLocalDate().lengthOfMonth() , 0,0);
        Date next = Date.valueOf(l.toLocalDate());
        
            //System.out.println("com.gym.gym_management.Controller.TransactionController.getMonthlyTransaction() "+next.after(now));
        
        List<TransactionModel> tr = transactionRepo.findAll();
            for (int i = 0; i < tr.size(); i++) {
                
                System.out.println("tr date "+tr.get(i).getDate() + " now "+ nowdate + " next "+ next);
                if(tr.get(i).getDate().after(nowdate) &&  tr.get(i).getDate().before(next)){
                    transaction.add(tr.get(i));
                }else if(tr.get(i).getDate().compareTo(next) == 0){
                    transaction.add(tr.get(i));
                }else if(tr.get(i).getDate().compareTo(nowdate) == 0){
                    transaction.add(tr.get(i));
                }
            }
        System.out.println("com.gym.gym_management.Controller.TransactionController.getMonthlyTransaction() "+tr.size());
        } catch (Exception e) {
            System.out.println("com.gym.gym_management.Controller.TransactionController.getMonthlyTransaction() "+e.getMessage());
        }

      
        return transaction;
    }
    
    @PostMapping("/getYearlyTransaction")
    public List<TransactionModel> getYearlyTransaction(String date){
        List<TransactionModel> transaction = new LinkedList<>();
        try {
             Date now =  Date.valueOf(LocalDate.parse(date));
             int year = now.toLocalDate().getYear();
             int month = now.toLocalDate().getMonthValue();
             int nextyear = year;
             if(month <= 3){
                year = year - 1;
             }else{
                nextyear = nextyear + 1;     
             }
             now = Date.valueOf(LocalDateTime.of(year , 4, 1,0,0).toLocalDate());
             Date next = Date.valueOf(LocalDateTime.of(nextyear , 3, 31,0,0).toLocalDate());                   
        System.out.println("com.gym.gym_management.Controller.TransactionController.getMonthlyTransaction() "+date +"  "+next);
        
        List<TransactionModel> tr = transactionRepo.findAll();
            for (int i = 0; i < tr.size(); i++) {
                
                //System.out.println("tr date "+tr.get(i).getDate() + " now "+ now + " next "+ next);
                if(tr.get(i).getDate().after(now) &&  tr.get(i).getDate().before(next)){
                    transaction.add(tr.get(i));
                }else if(tr.get(i).getDate().compareTo(next) == 0){
                    transaction.add(tr.get(i));
                }else if(tr.get(i).getDate().compareTo(now) == 0){
                    transaction.add(tr.get(i));
                }
            }
        //System.out.println("com.gym.gym_management.Controller.TransactionController.getMonthlyTransaction() "+tr.size());
        } catch (Exception e) {
            //System.out.println("com.gym.gym_management.Controller.TransactionController.getMonthlyTransaction() "+e.getMessage());
        }

      
        return transaction;
    }
     
}
