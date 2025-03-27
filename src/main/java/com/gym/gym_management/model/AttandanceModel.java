/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gym.gym_management.model;

import com.gym.gym_management.enums.AttandanceStatus;
import com.gym.gym_management.enums.AttandanceTime;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.sql.Date;
import java.sql.Timestamp;

/**
 *
 * @author tusha
 */
@Entity
@Table(name = "AttandanceData")
public class AttandanceModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @ManyToOne
    private UserModel user;
    private Date date;
    private AttandanceStatus attandanceStatus;
    private AttandanceTime attandanceTime;

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
     * @return the user
     */
    public UserModel getUser() {
        return user;
    }

    /**
     * @param user the user to set
     */
    public void setUser(UserModel user) {
        this.user = user;
    }

    /**
     * @return the date
     */
    public Date getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * @return the attandanceStatus
     */
    public AttandanceStatus getAttandanceStatus() {
        return attandanceStatus;
    }

    /**
     * @param attandanceStatus the attandanceStatus to set
     */
    public void setAttandanceStatus(AttandanceStatus attandanceStatus) {
        this.attandanceStatus = attandanceStatus;
    }

    /**
     * @return the attandanceTime
     */
    public AttandanceTime getAttandanceTime() {
        return attandanceTime;
    }

    /**
     * @param attandanceTime the attandanceTime to set
     */
    public void setAttandanceTime(AttandanceTime attandanceTime) {
        this.attandanceTime = attandanceTime;
    }

    
}
