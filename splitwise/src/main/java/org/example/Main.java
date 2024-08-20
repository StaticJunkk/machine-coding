package org.example;

import org.example.datalayer.model.UserEntity;
import org.example.service.ifaces.IExpenseService;
import org.example.service.impl.ExpenseService;
import org.example.service.model.ExpenseDTO;
import org.example.service.model.SplitType;

import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Integer count = 0;
        IExpenseService expenseManager = new ExpenseService();

        expenseManager.addUser(new UserEntity("u1", "User1", "yuvi@nkav", "9876543210"));
        expenseManager.addUser(new UserEntity("u2", "User2", "advadvav", "9876543210"));
        expenseManager.addUser(new UserEntity("u3", "User3", "avavava", "9876543210"));
        expenseManager.addUser(new UserEntity("u4", "User4", "advavv", "9876543210"));

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Welcome!");
            String command = scanner.nextLine();
            String[] commands = command.split(" ");
            String commandType = commands[0];

            switch (commandType) {
                case "SHOW":
                    System.out.println("Showing balances for all users");
                    List<String> resp;
                    if (commands.length == 1) {
                        resp = expenseManager.getBalance();
                    } else {
                        resp = expenseManager.getBalance(commands[1]);
                    }
                    resp.forEach(System.out::println);
                    break;
                case "EXPENSE":
                    System.out.println("Adding expense...");
                    count++;
                    String paidBy = commands[1];
                    Double amount = Double.parseDouble(commands[2]);
                    int noOfUsers = Integer.parseInt(commands[3]);
                    String expenseType = commands[4 + noOfUsers];
                    HashMap<String, Double> expenseList = new HashMap<>();
                    switch (expenseType) {
                        case "EQUAL":
                            System.out.println("Splitting equally");
                            for (int i = 0; i < noOfUsers; i++) {
                                expenseList.put(commands[4 + i], amount);
                            }
                            expenseManager.addExpense(new ExpenseDTO(String.valueOf(count),
                                    "Expnse",
                                    paidBy,
                                    paidBy,
                                    amount,
                                    SplitType.EQUAL,
                                    expenseList));
                            break;
                        case "EXACT":
                            for (int i = 0; i < noOfUsers; i++) {
                                expenseList.put(commands[4 + i], Double.parseDouble(commands[5 + noOfUsers + i]));
                            }
                            expenseManager.addExpense(new ExpenseDTO(String.valueOf(count),
                                    "Expnse",
                                    paidBy,
                                    paidBy,
                                    amount,
                                    SplitType.EXACT,
                                    expenseList));
                            break;
                        case "PERCENT":
                            for (int i = 0; i < noOfUsers; i++) {
                                expenseList.put(commands[4 + i], Double.parseDouble(commands[5 + noOfUsers + i]));
                            }
                            expenseManager.addExpense(new ExpenseDTO(String.valueOf(count),
                                    "Expnse",
                                    paidBy,
                                    paidBy,
                                    amount,
                                    SplitType.PERCENTAGE,
                                    expenseList));
                            break;
                    }
                    break;
            }
        }
    }
}


// EXPENSE u1 20 2 u1 u2 EQUAL
// EXPENSE u1 20 2 u1 u2 EXACT 15 5
// EXPENSE u1 20 2 u3 u2 EXACT 15 5
// SHOW
// SHOW u1