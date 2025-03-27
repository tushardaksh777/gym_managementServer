/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gym.gym_management.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.gym.gym_management.enums.PaymentMode;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.sql.Date;
import java.sql.Timestamp;
import org.hibernate.annotations.OnDelete;

/**
 *
 * @author tusha
 */
@Entity
public class TransactionModel {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    
    @ManyToOne
    private UserModel user;
    
    private PaymentMode paymentMode;
    private Date date;
    private int amount;
    private int registrationfee;
    private int monthlyFee;
    private int trademilFee;
    private int lightCharges;
    private int personalTraining;
    private int otherFee;
    private String feeNote;

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
     * @return the paymentMode
     */
    public PaymentMode getPaymentMode() {
        return paymentMode;
    }

    /**
     * @param paymentMode the paymentMode to set
     */
    public void setPaymentMode(PaymentMode paymentMode) {
        this.paymentMode = paymentMode;
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
     * @return the registrationfee
     */
    public int getRegistrationfee() {
        return registrationfee;
    }

    /**
     * @param registrationfee the registrationfee to set
     */
    public void setRegistrationfee(int registrationfee) {
        this.registrationfee = registrationfee;
    }

    /**
     * @return the monthlyFee
     */
    public int getMonthlyFee() {
        return monthlyFee;
    }

    /**
     * @param monthlyFee the monthlyFee to set
     */
    public void setMonthlyFee(int monthlyFee) {
        this.monthlyFee = monthlyFee;
    }

    /**
     * @return the trademilFee
     */
    public int getTrademilFee() {
        return trademilFee;
    }

    /**
     * @param trademilFee the trademilFee to set
     */
    public void setTrademilFee(int trademilFee) {
        this.trademilFee = trademilFee;
    }

    /**
     * @return the lightCharges
     */
    public int getLightCharges() {
        return lightCharges;
    }

    /**
     * @param lightCharges the lightCharges to set
     */
    public void setLightCharges(int lightCharges) {
        this.lightCharges = lightCharges;
    }

    /**
     * @return the personalTraining
     */
    public int getPersonalTraining() {
        return personalTraining;
    }

    /**
     * @param personalTraining the personalTraining to set
     */
    public void setPersonalTraining(int personalTraining) {
        this.personalTraining = personalTraining;
    }

    /**
     * @return the otherFee
     */
    public int getOtherFee() {
        return otherFee;
    }

    /**
     * @param otherFee the otherFee to set
     */
    public void setOtherFee(int otherFee) {
        this.otherFee = otherFee;
    }

    /**
     * @return the feeNote
     */
    public String getFeeNote() {
        return feeNote;
    }

    /**
     * @param feeNote the feeNote to set
     */
    public void setFeeNote(String feeNote) {
        this.feeNote = feeNote;
    }

    /**
     * @return the amount
     */
    public int getAmount() {
        return amount;
    }

    /**
     * @param amount the amount to set
     */
    public void setAmount(int amount) {
        this.amount = amount;
    }

    
    
}
