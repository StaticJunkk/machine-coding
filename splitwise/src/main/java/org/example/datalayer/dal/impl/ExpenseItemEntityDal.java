package org.example.datalayer.dal.impl;

import org.example.datalayer.dal.ifaces.IExpenseEntityDal;
import org.example.datalayer.dal.ifaces.IExpenseItemEntityDal;
import org.example.datalayer.dao.ExpenseDao;
import org.example.datalayer.dao.ExpenseItemDao;
import org.example.datalayer.model.ExpenseEntity;
import org.example.datalayer.model.ExpenseItemEntity;

public class ExpenseItemEntityDal implements IExpenseItemEntityDal {
    private ExpenseItemDao dao = new ExpenseItemDao();

    @Override
    public ExpenseItemEntity getExpenseItem(String id) {
        return dao.getExpenseItem(id);
    }

    @Override
    public void addExpense(ExpenseItemEntity expenseItemEntity) {
        dao.addExpenseItem(expenseItemEntity);
    }
}
