package org.example.datalayer.model;

public class ExpenseItemEntity {
    private String id;
    private String expenseId;
    private String owedBy;
    private Double amount;

    public String getId() {
        return this.id;
    }

    public void setId(String userId) {
        this.id = id;
    }

    public String getExpenseId() {
        return this.expenseId;
    }

    public void setExpenseId(String expenseId) {
        this.expenseId = expenseId;
    }

    public String getOwedBy() {
        return this.owedBy;
    }

    public void setOwedBy(String addedBy) {
        this.owedBy = owedBy;
    }

    public Double getAmount() {
        return this.amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

}
