package org.example.service.model;

import java.util.HashMap;

public class BalanceDTO {
    private String userName;
    private Double amount;
    private String transactedWith;

    public BalanceDTO(String userName, Double amount, String transactedWith) {
        this.amount = amount;
        this.userName = userName;
        this.transactedWith = transactedWith;
    }
    public String getUserName() {
        return this.userName;
    }


    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Double getAmount() {
        return this.amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getTransactedWith() {
        return this.transactedWith;
    }

    public void setTransactedWith(String transactedWith) {
        this.transactedWith = transactedWith;
    }

    public String getBalanceString(){
        String response = "User: " + userName;
        if (amount.compareTo((double) 0)<0){
            response += " owes amount: " + -1*this.amount + " to: " + this.transactedWith;
        } else {
            response += " is owed amount: " + this.amount + " by: " + this.transactedWith;
        }
        return response;
    }
}
