package org.example.datalayer.dal.impl;

import org.example.datalayer.dal.ifaces.IExpenseEntityDal;
import org.example.datalayer.dal.ifaces.IUserEntityDal;
import org.example.datalayer.dao.ExpenseDao;
import org.example.datalayer.dao.UserDao;
import org.example.datalayer.model.ExpenseEntity;
import org.example.datalayer.model.UserEntity;

import java.util.List;

public class ExpenseEntityDal implements IExpenseEntityDal {
    private ExpenseDao dao = new ExpenseDao();

    @Override
    public ExpenseEntity getExpense(String id) {
        return dao.getExpense(id);
    }

    @Override
    public void addExpense(ExpenseEntity expenseEntity) {
        dao.addExpense(expenseEntity);
    }
}
