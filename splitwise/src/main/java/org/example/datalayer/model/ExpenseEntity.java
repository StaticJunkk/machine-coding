package org.example.datalayer.model;

public class ExpenseEntity {
    private String id;
    private String desc;
    private String addedBy;
    private String paidBy;
    private Double amount;

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDesc() {
        return this.desc;
    }

    public String getPaidBy() {
        return this.paidBy;
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
