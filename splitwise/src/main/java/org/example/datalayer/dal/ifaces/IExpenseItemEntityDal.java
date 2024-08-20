package org.example.datalayer.dal.ifaces;

import org.example.datalayer.model.ExpenseItemEntity;

public interface IExpenseItemEntityDal {

    ExpenseItemEntity getExpenseItem(String id);

    void addExpense(ExpenseItemEntity expenseItemEntity);
}
