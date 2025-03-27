/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gym.gym_management.Controller;

import com.gym.gym_management.model.ProfileModel;
import com.gym.gym_management.repository.ProflieRepo;
import java.util.HashMap;
import java.util.List;
import javax.print.attribute.HashAttributeSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author tusha
 */
@RestController
@RequestMapping("/profile")
public class profileController {
    
    @Autowired
    ProflieRepo proflieRepo;
    
    @PostMapping("/addProfile")
    boolean addProfile(String name , String phoneNo , String email , String busAddress, String gstin , String udyogReg , String busDes, String state, String type , String busCategory){
        try {
            ProfileModel profile = new ProfileModel();
            profile.setName(name);
            profile.setPhoneNo(phoneNo);
            profile.setEmail(email);
            profile.setBusinessAddress(busAddress);
            profile.setGSTIN(gstin);
            profile.setUdyogRegistration(udyogReg);
            profile.setBusinessDes(busDes);
            profile.setState(state);
            profile.setBusinessType(busDes);
            profile.setBusinessCategory(busCategory);
            
            proflieRepo.save(profile);
            return true;
        } catch (Exception e) {
            System.out.println("com.gym.gym_management.Controller.profileController.addProfile() "+e.getMessage());
            return false;
        }
        
    }
    
    @PostMapping("/editProfile")
    HashMap editProfile(String name , String phoneNo , String email , String busAddress, String gstin , String udyogReg , String busDes, String state, String type , String busCategory){
        HashMap<String , String> response = new HashMap<>(); 
        try {
            long isexist = proflieRepo.count();
            
            
            if(isexist == 0){
                ProfileModel profile = new ProfileModel();
            profile.setName(name);
            profile.setPhoneNo(phoneNo);
            profile.setEmail(email);
            profile.setBusinessAddress(busAddress);
            profile.setGSTIN(gstin);
            profile.setUdyogRegistration(udyogReg);
            profile.setBusinessDes(busDes);
            profile.setState(state);
            profile.setBusinessType(busDes);
            profile.setBusinessCategory(busCategory);
            
            proflieRepo.save(profile);
            
            response.put("response", "true");
            //response.put("msg", "");
            return response;
            }
            
            
            ProfileModel profile = proflieRepo.findAll().get(0);
            
            profile.setName(name);
            profile.setPhoneNo(phoneNo);
            profile.setEmail(email);
            profile.setBusinessAddress(busAddress);
            profile.setGSTIN(gstin);
            profile.setUdyogRegistration(udyogReg);
            profile.setBusinessDes(busDes);
            profile.setState(state);
            profile.setBusinessType(busDes);
            profile.setBusinessCategory(busCategory);
            
            proflieRepo.save(profile);
            
            response.put("response", "true");
            //response.put("msg", "");
            return response;
            
        } catch (Exception e) {
            response.put("response", "true");
            //response.put("msg", "");
            return response;
        }
        
    }
    
    @PostMapping("/getProfile")
    HashMap getProfile(){
        HashMap<String , Object> response = new HashMap<>(); 
        List<ProfileModel> profiles = proflieRepo.findAll();
        try {
            
             //boolean exist = proflieRepo.existsById(1);
        long entryCount =proflieRepo.count();
        
        if(entryCount == 0){
            response.put("res", "false");
            return response;
        }
        } catch (Exception e) {
            System.out.println("com.gym.gym_management.Controller.profileController.getProfile() "+e.getMessage());
        }
        ProfileModel profile = profiles.get(0);
        
        
        
        
        
        
        response.put("res", "true");
        
        response.put("data" , profile);
        return response;
    }
}
