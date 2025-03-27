/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gym.gym_management.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

/**
 *
 * @author tusha
 */
@Entity
public class ProfileModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    
    private String name;
    private String phoneNo;
    private String email;
    private String businessAddress;
    private String GSTIN;
    private String udyogRegistration;
    private String businessDes;
    private String state;
    private String businessType;
    private String businessCategory;

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the phoneNo
     */
    public String getPhoneNo() {
        return phoneNo;
    }

    /**
     * @param phoneNo the phoneNo to set
     */
    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the businessAddress
     */
    public String getBusinessAddress() {
        return businessAddress;
    }

    /**
     * @param businessAddress the businessAddress to set
     */
    public void setBusinessAddress(String businessAddress) {
        this.businessAddress = businessAddress;
    }

    /**
     * @return the GSTIN
     */
    public String getGSTIN() {
        return GSTIN;
    }

    /**
     * @param GSTIN the GSTIN to set
     */
    public void setGSTIN(String GSTIN) {
        this.GSTIN = GSTIN;
    }

    /**
     * @return the udyogRegistration
     */
    public String getUdyogRegistration() {
        return udyogRegistration;
    }

    /**
     * @param udyogRegistration the udyogRegistration to set
     */
    public void setUdyogRegistration(String udyogRegistration) {
        this.udyogRegistration = udyogRegistration;
    }

    /**
     * @return the businessDes
     */
    public String getBusinessDes() {
        return businessDes;
    }

    /**
     * @param businessDes the businessDes to set
     */
    public void setBusinessDes(String businessDes) {
        this.businessDes = businessDes;
    }

    /**
     * @return the state
     */
    public String getState() {
        return state;
    }

    /**
     * @param state the state to set
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * @return the businessType
     */
    public String getBusinessType() {
        return businessType;
    }

    /**
     * @param businessType the businessType to set
     */
    public void setBusinessType(String businessType) {
        this.businessType = businessType;
    }

    /**
     * @return the businessCategory
     */
    public String getBusinessCategory() {
        return businessCategory;
    }

    /**
     * @param businessCategory the businessCategory to set
     */
    public void setBusinessCategory(String businessCategory) {
        this.businessCategory = businessCategory;
    }
    
    
    
}
