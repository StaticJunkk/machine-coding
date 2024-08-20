package org.example.service.ifaces;

import org.example.datalayer.model.UserEntity;
import org.example.service.model.ExpenseDTO;

import java.util.List;

public interface IExpenseService {

    void addExpense(ExpenseDTO expenseDTO);

    List<String> getBalance(String userId);

    List<String> getBalance();

    void addUser(UserEntity userEntity);
}
