package org.example.datalayer.dao;


import org.example.datalayer.model.ExpenseItemEntity;

import java.util.HashMap;

public class ExpenseItemDao {
    private final HashMap<String, ExpenseItemEntity> expenseItemEntityMap;

    public ExpenseItemDao() {
        this.expenseItemEntityMap = new HashMap<>();
    }

    public void addExpenseItem(ExpenseItemEntity expenseItemEntity) {
        this.expenseItemEntityMap.put(expenseItemEntity.getId(), expenseItemEntity);
    }

    public ExpenseItemEntity getExpenseItem(String id) {
        return this.expenseItemEntityMap.get(id);
    }

}
