package org.example.datalayer.dal.ifaces;

import org.example.datalayer.model.ExpenseEntity;

public interface IExpenseEntityDal {

    ExpenseEntity getExpense(String id);

    void addExpense(ExpenseEntity expenseEntity);
}
