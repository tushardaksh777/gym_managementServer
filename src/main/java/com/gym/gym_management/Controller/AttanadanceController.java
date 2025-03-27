/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gym.gym_management.Controller;

import com.gym.gym_management.enums.AttandanceStatus;
import com.gym.gym_management.enums.AttandanceTime;
import com.gym.gym_management.enums.UserStatus;
import com.gym.gym_management.model.AttandanceModel;
import com.gym.gym_management.model.ExpensesModel;
import com.gym.gym_management.model.UserModel;
import com.gym.gym_management.repository.AttandanceRepo;
import com.gym.gym_management.repository.UserRepo;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author tusha
 */
@RequestMapping("/Attandance")
@RestController
public class AttanadanceController {
    
    @Autowired
    AttandanceRepo attandanceRepo;
    
    @Autowired
    UserRepo userRepo;
    
    
    @PostMapping("/submitAttandance")
    public boolean submitAttandance(String registrationNo  , String date ,String time){
        Date date1 = Date.valueOf(LocalDate.parse(date));
        List<Integer> regList= new LinkedList<>();
        var s = registrationNo.split(",");
        
        for(int j =0 ; j <s.length ;j++){
           regList.add(Integer.valueOf(s[j]));
        }
        try {
            for(int i= 0 ; i< regList.size() ;i++) {
                AttandanceModel attandance = new AttandanceModel();
               
                UserModel user = userRepo.findByRegistrationNo(regList.get(i));
                 List<AttandanceModel> list = attandanceRepo.findByDateAndUser(date1 ,user);
                 System.out.println("Attandance present "+list.size());
                 if(list.isEmpty()){
                     if(time.equals("morning")){
                         attandance.setAttandanceTime(AttandanceTime.morning);
                     }else{
                         attandance.setAttandanceTime(AttandanceTime.evening);
                         
                     }
                 attandance.setAttandanceStatus(AttandanceStatus.present);
                attandance.setDate(date1);
                attandance.setUser(user);
                
                attandanceRepo.save(attandance);
                 }
               
                }
            return true;
        } catch (Exception e) {
            System.out.println("Submit attandance "+e.getMessage());
            return false;
        }
    }
    
    
    @PostMapping("/getUserByAttandanceStatus")
    public List<AttandanceModel> getUserByAttandanceStatus(String status , String date){
        Date date1 = Date.valueOf(LocalDate.parse(date));
        
        AttandanceStatus s = AttandanceStatus.present;
        if(status.equals("absent")){
            s = AttandanceStatus.absent;
        }
        List<AttandanceModel> a = attandanceRepo.findByDateAndAttandanceStatus(date1 , s);
        
        //List<UserModel> presentUser = new LinkedList<UserModel>();
         
//        for(int i =0 ; i < a.size();i++){
//            presentUser.add(a.get(i).getUser());
//        }
        
        return a;
    }
    
    @PostMapping("/finishDay")
    public boolean finishDay( String date , String time){
        Date date1 = Date.valueOf(LocalDate.parse(date));        
        try {
             Date currentDate = date1;
        List<Integer> presentuserId = new LinkedList<Integer>();
        List<AttandanceModel> todayAttandance = attandanceRepo.findByDateAndAttandanceStatus(currentDate, AttandanceStatus.present);
        List<AttandanceModel> todayAbsent = attandanceRepo.findByDateAndAttandanceStatus(currentDate, AttandanceStatus.absent);
        
        if(!todayAttandance.isEmpty()){
            
            for(int i = 0 ; i < todayAttandance.size() ;i++){
            presentuserId.add(todayAttandance.get(i).getUser().getId());
        
        }
        }
            
        if(!todayAbsent.isEmpty()){
            
            for(int j=0; j < todayAbsent.size() ; j++){
                presentuserId.add(todayAbsent.get(j).getUser().getId());
            }
        }
        List<UserModel> absentUsers = new LinkedList<>();
        if(presentuserId.isEmpty()){
           absentUsers  = userRepo.findByStatus(UserStatus.active);
        }else{
            absentUsers = userRepo.findByIdNotIn(presentuserId);
        }
            System.out.println("com.gym.gym_management.Controller.AttanadanceController.finishDay() "+ absentUsers.size());
        
        for(int j =0 ;j <absentUsers.size() ; j++){
           if(absentUsers.get(j).getStatus() == UserStatus.active){
                AttandanceModel attandance = new AttandanceModel();
            
            attandance.setAttandanceStatus(AttandanceStatus.absent);
            attandance.setDate(currentDate);
            attandance.setUser(absentUsers.get(j));
             if(time.equals("morning")){
                      attandance.setAttandanceTime(AttandanceTime.morning);
                }else{
                     attandance.setAttandanceTime(AttandanceTime.evening);
                         
            }
            
            attandanceRepo.save(attandance);
           }
        }
        
        //List<AttandanceModel> todayPresent = attandanceRepo.findByDateAndAttandanceStatus(currentDate, AttandanceStatus.present);
        
//        for(int i=0 ; i< activeUsers.size() ; i++){
//            
//            for(int j=0; j < todayPresent.size() ;j++){
//                
//                if(activeUsers.get(i).getRegistrationNo() != todayPresent.get(j).getUser().getRegistrationNo()){
//                    UserModel user = activeUsers.get(i);
//                    AttandanceModel attandanceModel = new AttandanceModel();
//                    
//                    attandanceModel.setAttandanceStatus(AttandanceStatus.absent);
//                    attandanceModel.setDate(currentDate);
//                    attandanceModel.setUser(user);
//                    
//                    attandanceRepo.save(attandanceModel);
//                }
//            }
//        }
        
        return true;
        } catch (Exception e) {
            System.out.println("com.gym.gym_management.Controller.AttanadanceController.finishDay() "+e.getMessage());
            return false;
        }
        
    }
    
    @RequestMapping("/getUserAttandance")
    public List<AttandanceModel> getUserAttandance(int registrationNo,String date){
        
      
        try {
            // Timestamp  currentTime = Timestamp.valueOf(date);
       //System.out.println("Date "+ Date.valueOf(LocalDate.now()) + " timestamp "+Timestamp.valueOf(LocalDateTime.now()) + "  request "+date);
       UserModel user = userRepo.findByRegistrationNo(registrationNo);
       Date c = Date.valueOf(LocalDate.parse(date));
       //c.toLocalDate().getDayOfMonth();
       Date date1 = Date.valueOf(LocalDate.of(c.toLocalDate().getYear(), c.toLocalDate().getMonthValue(), 1));
       
       Date date2 = Date.valueOf(LocalDate.of(c.toLocalDate().getYear(),c.toLocalDate().getMonthValue() , c.toLocalDate().lengthOfMonth()));
       
      
      List<AttandanceModel> attandances = attandanceRepo.findByUser(user.getId());
            System.out.println("com.gym.gym_management.Controller.AttanadanceController.getUserAttandance() "+attandances.size());
      return attandances;
      //List<Timestamp> timestamps = new LinkedList<Timestamp>();
        } catch (Exception e) {
            return new LinkedList<>();
        }
      
//      for(int i =0 ; i < attandances.size() ; i++){
//          Date attDate = attandances.get(i).getDate();
//          Timestamp s =Timestamp.valueOf(LocalDateTime.of(attDate.toLocalDate(), LocalTime.now()));
//          timestamps.add(s);
//      }
      
       
    }
    
    @PostMapping("/deleteAttandance")
    public boolean deleteAttandance(int id){
         try {
            AttandanceModel at = attandanceRepo.findById(id).get();
            at.setUser(null);
            attandanceRepo.save(at);
            attandanceRepo.deleteById(at.getId());
            return true;
        } catch (Exception e) {
             System.err.println("Delete Attandance "+e.getMessage());
            return false;
        }
    }
    
        @PostMapping("/deleteByList")
    public boolean deleteAttandanceList(String deletelist  , String date ,String status){
        //Date date1 = Date.valueOf(LocalDate.parse(date));
        List<Integer> regList= new LinkedList<>();
        var s = deletelist.split(",");
        
        for (String item : s) {
            regList.add(Integer.valueOf(item));
        }
            System.out.println("com.gym.gym_management.Controller.AttanadanceController.deleteAttandanceList() "+regList.toString());
        try {
            for(int i= 0 ; i< regList.size() ;i++) {
               // AttandanceModel attandance = new AttandanceModel();
               
                //UserModel user = userRepo.findByRegistrationNo(regList.get(i));
                 //List<AttandanceModel> list = attandanceRepo.findByDateAndUser(date1 ,user);
                 //System.out.println("Attandance present "+list.size());
                 int id = regList.get(i);
                  AttandanceModel at = attandanceRepo.findById(id).get();
                  
                  if(at != null ){
                  at.setUser(null);
                  attandanceRepo.save(at);
                  attandanceRepo.deleteById(at.getId());
                  }

                  
               
                }
            return true;
        } catch (Exception e) {
            System.out.println("Submit attandance "+e.getMessage());
            return false;
        }
    }
    
    
    @PostMapping("/getAttandanceForPdf")
    public List<HashMap> getAttandancePdf(String date){
        List<UserModel> activeusers = userRepo.findByStatus(UserStatus.active);
        
        Date c = Date.valueOf(LocalDate.parse(date));
        Date now = Date.valueOf(LocalDate.of(c.toLocalDate().getYear(), c.toLocalDate().getMonthValue(), 1));
        Date end = Date.valueOf(LocalDate.of(c.toLocalDate().getYear(),c.toLocalDate().getMonthValue() , c.toLocalDate().lengthOfMonth()));
        List<HashMap> data = new LinkedList<>();
        if(activeusers.size() > 0){
                       activeusers.sort((o1, o2) -> {
                           return Integer.compare(o1.getRegistrationNo(), o2.getRegistrationNo());
                       });
        }
        
        for (int i = 0; i < activeusers.size(); i++) {
        
         List<AttandanceModel> attandances = attandanceRepo.findByDateAndUser(now, end, activeusers.get(i).getId());
         HashMap<String , Object> s = new HashMap<>();
         s.put("User", activeusers.get(i));
         s.put("Attandances", attandances);  
         data.add(s);
        }
   
         
         
         return data;   
    }
    
}
