/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.gym.gym_management.Controller;

import com.gym.gym_management.enums.Expenses;
import com.gym.gym_management.enums.ExpensesCategory;
import com.gym.gym_management.enums.PaymentMode;
import com.gym.gym_management.model.ExpensesModel;
import com.gym.gym_management.repository.ExpensesRepo;
import java.awt.image.RescaleOp;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
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
@RequestMapping("/Expenses")
public class ExpensesController {
    
    @Autowired
    ExpensesRepo expensesRepo;
    
    
    @PostMapping("/addExpenses")
    public boolean addExpenses(String expenses , String title , String amount , String paymentMode , String note , String date , String expensesCategory){
//        System.out.println("com.gym.gym_management.Controller.ExpensesController.addExpenses()");
        try {
             ExpensesModel expensesModel = new ExpensesModel();
        
        if(expenses.equals("gym")){
            expensesModel.setExpenses(Expenses.gym);
        }else{
            expensesModel.setExpenses(Expenses.other);
        }
        expensesModel.setTitle(title);
        expensesModel.setAmount(Integer.parseInt(amount));
        
        if(paymentMode.equals("online")){
            expensesModel.setPaymentMode(PaymentMode.online);
        }else{
            expensesModel.setPaymentMode(PaymentMode.offline);
        }
        ExpensesCategory exp = ExpensesCategory.Other;
        
            switch (expensesCategory) {
                case "grocery":
                    exp = ExpensesCategory.grocery;
                    break;
                case "offlineShopping":
                    exp = ExpensesCategory.offlineShopping;
                    break;
                case "onlineShopping":
                    exp = ExpensesCategory.onlineShopping;
                    break;
                case "homeAndElectricity":
                    exp = ExpensesCategory.homeAndElectricity;
                    break;
                case "Oil":
                    exp = ExpensesCategory.Oil;
                    break;
                case "EMI":
                    exp = ExpensesCategory.EMI;
                    break;
                case "Recharge":
                    exp = ExpensesCategory.Recharge;
                    break;
                                
                default:
                    exp = ExpensesCategory.Other;
            }
        
        expensesModel.setExpensesCategory(exp);
        expensesModel.setNote(note);
        expensesModel.setDate(Date.valueOf(LocalDate.parse(date)));
           // System.out.println("com.gym.gym_management.Controller.ExpensesController.addExpenses() "+expensesModel.getId());
        
        expensesRepo.save(expensesModel);
        
        
        return true;
        } catch (Exception e) {
            System.out.println("com.gym.gym_management.Controller.ExpensesController.addExpenses() "+e.getMessage());
            return false;
        }
        
    }
    
    @PostMapping("/gymExpenses")
    public List<ExpensesModel> getGymExpenses(String date){
        List<ExpensesModel> expensesList = new LinkedList<>();
        try {
            
            Date now = Date.valueOf(LocalDate.parse(date));
            
            Date firstDate = Date.valueOf(LocalDate.of(now.toLocalDate().getYear(), now.toLocalDate().getMonth() , 1));
            Date endDate = Date.valueOf(LocalDate.of(now.toLocalDate().getYear(), now.toLocalDate().getMonth() , now.toLocalDate().getMonth().maxLength()));
            
            List<ExpensesModel> list = expensesRepo.findByDate(firstDate, endDate);
            
            for (int i=0; i<list.size() ;i++){
                if(list.get(i).getExpenses() == Expenses.gym){
                    expensesList.add(list.get(i));
                }
            }
            
        } catch (Exception e) {
            System.out.println("com.gym.gym_management.Controller.ExpensesController.getGymExpenses() "+e.getMessage());
        }
        if(!expensesList.isEmpty()){
            Collections.sort(expensesList, new Comparator<ExpensesModel>() {
  public int compare(ExpensesModel o1, ExpensesModel o2) {
      return o2.getDate().compareTo(o1.getDate());
  }
});
        }
        return  expensesList;
    }
    @PostMapping("/totalExpenses")
    public List<ExpensesModel> gettotalExpenses(String date){
        Date now =  Date.valueOf(LocalDate.parse(date));  
        //LocalDateTime.of(0, Month.MARCH, 0, 0, 0);
        LocalDateTime l = LocalDateTime.of(now.toLocalDate().getYear(), now.toLocalDate().getMonth(), now.toLocalDate().lengthOfMonth() , 0,0);
        Date next = Date.valueOf(l.toLocalDate());
        List<ExpensesModel> expensesList = expensesRepo.findByDate(now , next);
        return  expensesList;
    }
    
    @PostMapping("/otherExpenses")
    public List<ExpensesModel> getotherExpenses(String date){
        List<ExpensesModel> expensesList = new LinkedList<>();
        try {
            Date now = Date.valueOf(LocalDate.parse(date));
            
            Date firstDate = Date.valueOf(LocalDate.of(now.toLocalDate().getYear(), now.toLocalDate().getMonth() , 1));
            Date endDate = Date.valueOf(LocalDate.of(now.toLocalDate().getYear(), now.toLocalDate().getMonth() , now.toLocalDate().getMonth().maxLength()));
            
            List<ExpensesModel> list = expensesRepo.findByDate(firstDate, endDate);
            System.out.println("com.gym.gym_management.Controller.ExpensesController.getotherExpenses() "+ list.size());
            for (int i=0; i<list.size() ;i++){
                if(list.get(i).getExpenses() == Expenses.other){
                    expensesList.add(list.get(i));
                }
            }
        if(!expensesList.isEmpty()){
    
            Collections.sort(expensesList, new Comparator<ExpensesModel>() {
            public int compare(ExpensesModel o1, ExpensesModel o2) {
            return o1.getDate().compareTo(o2.getDate());
              }
            });
        }
        } catch (Exception e) {
        }
        
        
        return  expensesList;
    }
    
        @PostMapping("/getpdfExpenses")
    public List<ExpensesModel> getpdfExpenses(String date , String time , String expense){
        List<ExpensesModel> expensesList = new LinkedList<>();
        try {
            Date now = Date.valueOf(LocalDate.parse(date));
            
            Date firstDate;// = Date.valueOf(LocalDate.of(now.toLocalDate().getYear(), now.toLocalDate().getMonth() , 1));
            Date endDate;// = Date.valueOf(LocalDate.of(now.toLocalDate().getYear(), now.toLocalDate().getMonth() , now.toLocalDate().getMonth().maxLength()));
            Expenses exp = Expenses.other;
            
            if(expense.equals("gym")){
                exp = Expenses.gym;
            }
            if(time.equals("monthly")){
                //monthly
                firstDate = Date.valueOf(LocalDate.of(now.toLocalDate().getYear(), now.toLocalDate().getMonth() , 1));
                endDate = Date.valueOf(LocalDate.of(now.toLocalDate().getYear(), now.toLocalDate().getMonth() , now.toLocalDate().getMonth().maxLength()));

            }else{
                //yearly
               firstDate = Date.valueOf(LocalDateTime.of(now.toLocalDate().getYear() , 4,1,0,0).toLocalDate());
               endDate = Date.valueOf(LocalDateTime.of(now.toLocalDate().getYear()+1, 3 , 31 , 0,0).toLocalDate());
            }
            List<ExpensesModel> list = expensesRepo.findByDate(firstDate, endDate);
            System.out.println("com.gym.gym_management.Controller.ExpensesController"+ list.size() +" "+exp.name());
            if(time.equals("monthly")){
                for (int i=0; i<list.size() ;i++){
                    
                if(expense.equals("both")){
                    expensesList.add(list.get(i));
                }else{
                    if(list.get(i).getExpenses() == exp){
                    expensesList.add(list.get(i));
                }
                }
                
                
                
            }
            }else{
                expensesList.addAll(list);
            }
        if(!expensesList.isEmpty()){
    
            Collections.sort(expensesList, new Comparator<ExpensesModel>() {
            public int compare(ExpensesModel o1, ExpensesModel o2) {
            return o1.getDate().compareTo(o2.getDate());
              }
            });
        }
        } catch (Exception e) {
        }
        
        
        return  expensesList;
    }
    
    @PostMapping("/deleteExpenses")
    public boolean deleteExpense(int id){
        try {
            ExpensesModel expensesModel = expensesRepo.findById(id).get();
            expensesRepo.delete(expensesModel);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

        @PostMapping("/editExpenses")
    public boolean editExpenses(int id , String expenses , String title , String amount , String paymentMode , String note , String date , String expensesCategory){
        System.out.println("com.gym.gym_management.Controller.ExpensesController.addExpenses() "+ paymentMode);
        try {
            
             ExpensesModel expensesModel = expensesRepo.findById(id).get();
        
        if(expenses.equals("gym")){
            expensesModel.setExpenses(Expenses.gym);
        }else{
            expensesModel.setExpenses(Expenses.other);
        }
        expensesModel.setTitle(title);
        expensesModel.setAmount(Integer.parseInt(amount));
        
        if(paymentMode.equals("online")){
            expensesModel.setPaymentMode(PaymentMode.online);
        }else{
            expensesModel.setPaymentMode(PaymentMode.offline);
        }
//        ExpensesCategory = 
//        if(){
//            
//        }
        ExpensesCategory exp = ExpensesCategory.Other;
        
            switch (expensesCategory) {
                case "grocery":
                    exp = ExpensesCategory.grocery;
                    break;
                case "offlineShopping":
                    exp = ExpensesCategory.offlineShopping;
                    break;
                case "onlineShopping":
                    exp = ExpensesCategory.onlineShopping;
                    break;
                case "homeAndElectricity":
                    exp = ExpensesCategory.homeAndElectricity;
                    break;
                case "Oil":
                    exp = ExpensesCategory.Oil;
                    break;
                case "EMI":
                    exp = ExpensesCategory.EMI;
                    break;
                case "Recharge":
                    exp = ExpensesCategory.Recharge;
                    break;
                                
                default:
                    exp = ExpensesCategory.Other;
            }
        expensesModel.setExpensesCategory(exp);
        expensesModel.setNote(note);
        expensesModel.setDate(Date.valueOf(LocalDate.parse(date)));
        
        expensesRepo.save(expensesModel);
        
        
        return true;
        } catch (Exception e) {
            return false;
        }
        
    }
}
