package org.example.datalayer.dao;


import org.example.datalayer.model.ExpenseEntity;

import java.util.HashMap;

public class ExpenseDao {
    private final HashMap<String, ExpenseEntity> expenseEntityMap;

    public ExpenseDao() {
        this.expenseEntityMap = new HashMap<>();
    }

    public void addExpense(ExpenseEntity expenseEntity) {
        this.expenseEntityMap.put(expenseEntity.getId(), expenseEntity);
    }

    public ExpenseEntity getExpense(String id) {
        return this.expenseEntityMap.get(id);
    }

}
