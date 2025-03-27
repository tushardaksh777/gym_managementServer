/*
\ * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.gym.gym_management.Controller;

import com.gym.gym_management.enums.Expenses;
import com.gym.gym_management.enums.ExpensesCategory;
import com.gym.gym_management.enums.PaymentMode;
import com.gym.gym_management.enums.UserStatus;
import com.gym.gym_management.model.AttandanceModel;
import com.gym.gym_management.model.ExpensesModel;
import com.gym.gym_management.model.ImageModel;
import com.gym.gym_management.model.TransactionModel;
import com.gym.gym_management.model.UserModel;
import com.gym.gym_management.repository.AttandanceRepo;
import com.gym.gym_management.repository.ImageRepo;
import com.gym.gym_management.repository.TransactionRepo;
import com.gym.gym_management.repository.UserRepo;
import java.sql.Date;
import java.util.List;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author tusha
 */
@RestController
@RequestMapping("/User")
public class UserController {
    
    @Autowired
    UserRepo userRepo;
    
    @Autowired
    TransactionRepo transactionRepo;
    
    @Autowired
    ImageRepo imageRepo;
    
    @Autowired
    AttandanceRepo attandanceRepo;
    
    @Autowired
    ExpensesController expensesController;
    
    @Autowired
    TransactionController transactionController;

    @PostMapping("/testUser")
    public ResponseEntity getUser(){
        
       
        HashMap<String , String> s= new HashMap<>();
        s.put("User1", "Tusahr");
        s.put("User2", "sajdndkjabn");
        s.put("TimeStamp", Timestamp.valueOf(LocalDateTime.now()).toString());
        
        return  ResponseEntity.ok(s);        
    }
    
    @PostMapping("/getActiveUser")
    public List<UserModel> getactiveUser(){
        List<UserModel> activeUsers = userRepo.findByStatus(UserStatus.active);
        return  activeUsers; 
    }
    
    @PostMapping("/getInactiveUser")
    public List<UserModel> getInactiveUser(){
        List<UserModel> inactiveUsers = userRepo.findByStatus(UserStatus.inactive);
        return  inactiveUsers; 
    }
    
    @PostMapping("/getAllUser")
    public List<UserModel> getAllUser(){
        List<UserModel> allUsers = userRepo.findAll();
        return  allUsers; 
    }
    
    @PostMapping("/addUser")
    public HashMap addUser(int registrationNo , String name, String fatherName, String husbandName , String phoneNo , String address , String joiningDate , String status){
        HashMap<String , String> response = new HashMap<>(); 
        try {
             UserModel u =null;
            try {
                System.out.println("com.gym.gym_management.Controller.UserController.addUser() reg "+registrationNo);
                 u = userRepo.findByRegistrationNo(registrationNo);
            } catch (Exception e) {
                System.out.println("com.gym.gym_management.Controller.UserController.addUser() " + e.getMessage());
                response.put("response", "false");
                response.put("msg", "registration no. already present");
                return response;
            }
            if(u != null){
                System.out.println("com.gym.gym_management.Controller.UserController.addUser() User Null");
                response.put("response", "false");
                response.put("msg", "registration no. already present");
                return response;
            }
            UserModel user = new UserModel();
         
         user.setRegistrationNo(registrationNo);
         user.setName(name);
         user.setFatherName(fatherName);
         user.setHusbandName(husbandName);
         user.setPhoneNo(phoneNo);
         user.setAddress(address);
         user.setJoiningDate(Date.valueOf(LocalDate.parse(joiningDate)));
//         Date date = Date.valueOf(Timestamp.valueOf(joiningDate).toLocalDateTime().toLocalDate());
//             LocalDate s = date.toLocalDate();
             Date nextFeeSubmitDate = Date.valueOf(LocalDate.parse(joiningDate));
             user.setFeeSubmitDate(nextFeeSubmitDate);
             
            if(status.equals("active")){
                user.setStatus(UserStatus.active);
            }else{
                user.setStatus(UserStatus.inactive);
                user.setInactiveDate(Date.valueOf(LocalDate.now()));
            }
             
         
         
         userRepo.save(user);
         
        
        
        response.put("response", "true");
        
        return response;
        } catch (Exception e) {
             System.out.println("ErrorUser" +e.getMessage());
            response.put("response", "false");
            response.put("msg", "You have entered wrong value please check them !");
        
        return response;
        }
    }
    
    @PostMapping("/editUser")
    public HashMap editUser(int id,  int registrationNo , String name, String fatherName,String husbandName ,String phoneNo , String address , String joiningDate , String status){
        HashMap<String , String> response = new HashMap<>(); 
        try {
        
        UserModel user = userRepo.findById(id).get();
        UserModel u =null;
        try {
                 u = userRepo.findByRegistrationNo(registrationNo);
            } catch (Exception e) {
                response.put("response", "false");
                response.put("msg", "registration no. already present");
                return response;
            }
            if(u != null && u.getId() != id){
                response.put("response", "false");
                response.put("msg", "registration no. already present");
                return response;
            }
        
        user.setRegistrationNo(registrationNo);
        user.setName(name);
        user.setFatherName(fatherName);
        user.setPhoneNo(phoneNo);
        user.setHusbandName(husbandName);
        //if(user.getJoiningDate().compareTo(Timestamp.valueOf(joiningDate)) != 0 ){
       
        //}
        if(Date.valueOf(LocalDate.parse(joiningDate)).compareTo(user.getJoiningDate()) != 0){
            user.setFeeSubmitDate(Date.valueOf(LocalDate.parse(joiningDate)));
        }
         user.setJoiningDate(Date.valueOf(LocalDate.parse(joiningDate)));
         
        if(status.equals("active")){
            
             //LocalDate s = date.toLocalDate();
             //Date nextFeeSubmitDate = Date.valueOf(LocalDate.of(s.getYear(),Month.of(s.getMonth().getValue()+1) , s.getDayOfMonth()));
           
            
//             user.setFeeSubmitDate(nextFeeSubmitDate);
             user.setStatus(UserStatus.active);
             
         }else{
             //user.setFeeSubmitDate(date);
             user.setStatus(UserStatus.inactive);
            
         }
        
        userRepo.save(user);
        response.put("response", "true");
        //response.put("msg", "registration no. already present");
            return response;
       
        } catch (Exception e) {
        	System.out.print("Execption "+e.toString());
            response.put("response", "false");
            response.put("msg", "");
            return response;
           
        }
    }
    
    @PostMapping("/userStatus")
    public UserModel changeUserStatus(int registationNo ,String status , String date){
        try {
            UserStatus userStatus = UserStatus.active;
            UserModel user = userRepo.findByRegistrationNo(registationNo);
            if(status.equals("inactive")){
                userStatus = UserStatus.inactive;
                user.setInactiveDate(Date.valueOf(LocalDate.parse(date)));
            }else{
                user.setActiveDate(Date.valueOf(LocalDate.parse(date)));
            }
            
            user.setStatus(userStatus);
            
            userRepo.save(user);
            
            return user;
        } catch (Exception e) {
            return new UserModel();
        }
    }
    
    @PostMapping("/testimage")
    public String testImage(String name , MultipartFile image){
        return image.getName()+"  " +name;
    }
    
    @PostMapping("/getImage")
    public List<ImageModel> getImage(){
        List<ImageModel> images = imageRepo.findAll();
        return images;
    }
    
    @PostMapping("/getUser")
    public UserModel getUser(int userId){
        UserModel user = userRepo.findById(userId).get();
        return user;
    }
    @PostMapping("/deleteUser")
    public boolean deleteUser(int id){
        try {
            UserModel user = userRepo.findById(id).get();
            List<TransactionModel> list =  transactionRepo.findByUser(user);
            List<AttandanceModel> attandance = attandanceRepo.findByUser(user.getId());
            
            for(int i =0 ; i< list.size() ; i++){
                TransactionModel tr = list.get(i);
                tr.setUser(null);
                transactionRepo.save(tr);
                transactionRepo.deleteById(tr.getId());
            }
            for(int i =0 ; i< attandance.size() ; i++){
                AttandanceModel at = attandance.get(i);
                at.setUser(null);
                attandanceRepo.save(at);
                attandanceRepo.deleteById(at.getId());
            }
            
            userRepo.deleteById(user.getId());
        return true;
        } catch (Exception e) {
            System.out.println("com.gym.gym_management.Controller.UserController.deleteUser() "+e.getMessage());
            return false;
        }
    }
    @PostMapping("/getProfitLoss")
    public HashMap getProfitLossData(String date){
        
        int gymOnlineExpense = 0;
        int gymOfflineExpense=0;
        int otherOnlineExpense =0;
        int otherOfflineExpense =0;
        int totalFeesOnline =0;
        int totalFeesOffline =0;
        
        int gymExpMonth;
        int otherExpMonth;
        int feesExpMonth;        
        
        Date now = Date.valueOf(LocalDate.parse(date));
            
           // Date firstDate;// = Date.valueOf(LocalDate.of(now.toLocalDate().getYear(), now.toLocalDate().getMonth() , 1));
           // Date endDate;// = Date.valueOf(LocalDate.of(now.toLocalDate().getYear(), now.toLocalDate().getMonth() , now.toLocalDate().getMonth().maxLength()));       
        Date firstDate = Date.valueOf(LocalDate.of(now.toLocalDate().getYear(), now.toLocalDate().getMonth() , 1));
        Date endDate = Date.valueOf(LocalDate.of(now.toLocalDate().getYear(), now.toLocalDate().getMonth() , now.toLocalDate().getMonth().maxLength()));
        
        List<ExpensesModel> expenses = expensesController.getpdfExpenses(date, "yearly", "gym");
                       
        List<ExpensesModel> gymExpenses = new LinkedList<>(); // = expensesController.getpdfExpenses(date, "yearly", "gym");
        
        List<ExpensesModel> otherExpenses = new LinkedList<>(); // = expensesController.getpdfExpenses(date, "yearly", "other");
        
        for (int i = 0; i < expenses.size(); i++) {
            if(expenses.get(i).getExpenses() == Expenses.gym){
                gymExpenses.add(expenses.get(i));
            }else{
                otherExpenses.add(expenses.get(i));
            }
            //System.out.println(expenses.get(i).getExpenses() +" Expenses "+expenses.get(i).getDate());
        }
        
        List<TransactionModel> totalFees = transactionController.getYearlyTransaction(date);
        
        //System.out.println("Gym exp "+gymExpenses.size() +" Other exp "+ otherExpenses.size());
        
        gymExpenses.sort((o1, o2) -> o1.getDate().compareTo(o2.getDate()));
        otherExpenses.sort((o1, o2) -> o1.getDate().compareTo(o2.getDate()));
        totalFees.sort((o1, o2) -> o1.getDate().compareTo(o2.getDate()));
        
//        System.out.println("Total Fees "+totalFees.get(0).getDate() + " Last "+totalFees.get(totalFees.size() -1 ).getDate());
//        System.out.println("Gym Exp "+gymExpenses.get(0).getDate() + " Last "+gymExpenses.get(gymExpenses.size() -1 ).getDate());
//        System.out.println("Other Exp Fees "+otherExpenses.get(0).getDate() + " Last "+otherExpenses.get(otherExpenses.size() -1 ).getDate());
           
        HashMap<String , HashMap> data = new HashMap<>();
        HashMap<String , String> gymExpMonthData = new HashMap<>();
        HashMap<String , String> otherExpMonthData = new HashMap<>();
        HashMap<String , String> totalFeeMonthData = new HashMap<>();
        
        HashMap<String , HashMap> gymExpYearData = new HashMap<>();
        HashMap<String , HashMap> otherExpYearData = new HashMap<>();
        HashMap<String , HashMap> totalFeeYearData = new HashMap<>();
        
        
        if(!gymExpenses.isEmpty()){
            
            gymExpMonth = gymExpenses.get(0).getDate().toLocalDate().getMonthValue();
        for (int i = 0; i < gymExpenses.size(); i++) {
            ExpensesModel t = gymExpenses.get(i);
            if(t.getDate().toLocalDate().getMonthValue() != gymExpMonth || gymExpenses.size() == i+1){
            
            gymExpMonthData.put("gymOnlineExp", String.valueOf(gymOnlineExpense));
            gymExpMonthData.put("gymOfflineExp", String.valueOf(gymOfflineExpense));
            gymExpMonthData.put("gymTotalExp", String.valueOf(gymOnlineExpense + gymOfflineExpense));
             
            gymExpYearData.put(Month.of(gymExpMonth).name(), gymExpMonthData);
            
            gymExpMonthData = new HashMap<>();
            gymOnlineExpense =0;
            gymOfflineExpense =0;
            //System.out.println("Gym exp Month"+gymExpMonthData.toString() + " Month "+ Month.of(gymExpMonth));

            gymExpMonth = t.getDate().toLocalDate().getMonthValue();
            }
            if(t.getPaymentMode() == PaymentMode.online){
            gymOnlineExpense = gymOnlineExpense + t.getAmount();
            }else{
            gymOfflineExpense = gymOfflineExpense + t.getAmount();
            }
        }
            
        }
        
        if(!otherExpenses.isEmpty()){
            
            otherExpMonth = otherExpenses.get(0).getDate().toLocalDate().getMonthValue();
            for (int i = 0; i < otherExpenses.size(); i++) {
            ExpensesModel t = otherExpenses.get(i);
            
            if(t.getDate().toLocalDate().getMonthValue() != otherExpMonth || otherExpenses.size() == i+1){
            
            otherExpMonthData.put("otherOnlineExp", String.valueOf(otherOnlineExpense));
            otherExpMonthData.put("otherOfflineExp", String.valueOf(otherOfflineExpense));
            otherExpMonthData.put("otherTotalExp", String.valueOf(otherOnlineExpense + otherOfflineExpense));
             
            otherExpYearData.put(Month.of(otherExpMonth).name(), otherExpMonthData);
            
            otherExpMonthData = new HashMap<>();
            otherOnlineExpense =0;
            otherOfflineExpense =0;
            //System.out.println("Other exp Month"+otherExpMonthData.toString() + " Month "+ Month.of(otherExpMonth));
            
            otherExpMonth = t.getDate().toLocalDate().getMonthValue();
            }
            
            if(t.getPaymentMode() == PaymentMode.online){
            otherOnlineExpense = otherOnlineExpense + t.getAmount();
            }else{
            otherOfflineExpense = otherOfflineExpense + t.getAmount();
            }
        }
        
        }
        
        if(!totalFees.isEmpty()){
            
            feesExpMonth = totalFees.get(0).getDate().toLocalDate().getMonthValue();
            for (int i = 0; i < totalFees.size(); i++) {
            TransactionModel t = totalFees.get(i);
            
            if(t.getDate().toLocalDate().getMonthValue() != feesExpMonth || totalFees.size() == i+1){
            
            totalFeeMonthData.put("totalOnlineFees", String.valueOf(totalFeesOnline));
            totalFeeMonthData.put("totalOfflineFees", String.valueOf(totalFeesOffline));
            totalFeeMonthData.put("totalFees", String.valueOf(totalFeesOnline + totalFeesOffline));
             
            totalFeeYearData.put(Month.of(feesExpMonth).name(), totalFeeMonthData);
            
            totalFeeMonthData = new HashMap<>();
            totalFeesOnline =0;
            totalFeesOffline =0;
            
            //System.out.println("Total fee Month"+totalFeeMonthData.toString()+ " Month "+ Month.of(feesExpMonth));
            
            feesExpMonth = t.getDate().toLocalDate().getMonthValue();
            }
            
            
            if(t.getPaymentMode() == PaymentMode.online){
            totalFeesOnline = totalFeesOnline + t.getAmount();
            }else{
            totalFeesOffline = totalFeesOffline + t.getAmount();
            }
        }
            
        }
        
               
               
        
        data.put("gymExp", gymExpYearData);
        data.put("otherExp", otherExpYearData);
        data.put("totalFee", totalFeeYearData);
       
        
        return data;
        
    }
    

}
