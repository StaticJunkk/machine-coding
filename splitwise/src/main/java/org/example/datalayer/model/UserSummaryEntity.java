package org.example.datalayer.model;

public class UserSummaryEntity {
    private String id;
    private String userId;
    private Double amount;
    private String transactedWith;


    public String getUserId() {
        return this.userId;
    }

    public String getId() {
        return this.id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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


}
