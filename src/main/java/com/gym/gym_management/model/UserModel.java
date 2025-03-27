/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gym.gym_management.model;

import com.gym.gym_management.enums.UserStatus;
import jakarta.annotation.Generated;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.sql.Date;
import java.sql.Timestamp;

/**
 *
 * @author tusha
 */
@Entity
@Table(name = "UserData")
public class UserModel {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private int registrationNo;
    private String name;
    private String fatherName;
    private String husbandName;
    private String phoneNo;
    private String address;
    private Date joiningDate;
    private Date feeSubmitDate;
    private Date inactiveDate;
    private Date activeDate;
    private UserStatus status;

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
     * @return the registrationNo
     */
    public int getRegistrationNo() {
        return registrationNo;
    }

    /**
     * @param registrationNo the registrationNo to set
     */
    public void setRegistrationNo(int registrationNo) {
        this.registrationNo = registrationNo;
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
     * @return the fatherName
     */
    public String getFatherName() {
        return fatherName;
    }

    /**
     * @param fatherName the fatherName to set
     */
    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
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
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return the joiningDate
     */
    public Date getJoiningDate() {
        return joiningDate;
    }

    /**
     * @param joiningDate the joiningDate to set
     */
    public void setJoiningDate(Date joiningDate) {
        this.joiningDate = joiningDate;
    }

    /**
     * @return the status
     */
    public UserStatus getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(UserStatus status) {
        this.status = status;
    }

    /**
     * @return the registraionFee
     */
   
    /**
     * @return the feeSubmitDate
     */
    public Date getFeeSubmitDate() {
        return feeSubmitDate;
    }

    /**
     * @param feeSubmitDate the feeSubmitDate to set
     */
    public void setFeeSubmitDate(Date feeSubmitDate) {
        this.feeSubmitDate = feeSubmitDate;
    }

    /**
     * @return the husbandName
     */
    public String getHusbandName() {
        return husbandName;
    }

    /**
     * @param husbandName the husbandName to set
     */
    public void setHusbandName(String husbandName) {
        this.husbandName = husbandName;
    }

    /**
     * @return the inactiveDate
     */
    public Date getInactiveDate() {
        return inactiveDate;
    }

    /**
     * @param inactiveDate the inactiveDate to set
     */
    public void setInactiveDate(Date inactiveDate) {
        this.inactiveDate = inactiveDate;
    }

    /**
     * @return the activeDate
     */
    public Date getActiveDate() {
        return activeDate;
    }

    /**
     * @param activeDate the activeDate to set
     */
    public void setActiveDate(Date activeDate) {
        this.activeDate = activeDate;
    }
    
}
