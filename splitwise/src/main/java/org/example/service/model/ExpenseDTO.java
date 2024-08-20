package org.example.service.model;

import java.util.HashMap;

public class ExpenseDTO {
    private String id;
    private String desc;
    private String addedBy;
    private String paidBy;
    private Double amount;
    private SplitType splitType;
    private HashMap<String, Double> splitBetween;

    public ExpenseDTO(String id,
                      String desc,
                      String addedBy,
                      String paidBy,
                      Double amount,
                      SplitType splitType,
                      HashMap<String, Double> splitBetween) {
        this.id = id;
        this.desc = desc;
        this.addedBy = addedBy;
        this.paidBy = paidBy;
        this.amount = amount;
        this.splitType = splitType;
        this.splitBetween = splitBetween;
    }

    public SplitType getSplitType() {
        return this.splitType;
    }

    public void setSplitType(SplitType splitType) {
        this.splitType = splitType;
    }

    public HashMap<String, Double> getSplitBetween() {
        return this.splitBetween;
    }

    public void setSplitBetween(HashMap<String, Double> splitBetween) {
        this.splitBetween = splitBetween;
    }

    public String getDesc() {
        return this.desc;
    }

    public String getPaidBy() {
        return this.paidBy;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
         this.id = id;
    }

    public void setPaidBy(String paidBy) {
        this.paidBy = paidBy;
    }

    public String getAddedBy() {
        return this.addedBy;
    }

    public void setAddedBy(String addedBy) {
        this.addedBy = addedBy;
    }

    public Double getAmount() {
        return this.amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public void setName(String desc) {
        this.desc = desc;
    }
}
