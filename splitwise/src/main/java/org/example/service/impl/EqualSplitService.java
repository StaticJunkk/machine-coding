package org.example.service.impl;

import org.example.service.ifaces.ISplitService;

import java.util.HashMap;

public class EqualSplitService implements ISplitService {
    @Override
    public HashMap<String, Double> split(HashMap<String, Double> expenseList,  Double amount) {
        int numberOfPeople = expenseList.size();
        Double splitAmount = amount / numberOfPeople;
        expenseList.forEach((person, percentage) -> {
            expenseList.put(person,  splitAmount);
        });
        return expenseList;
    }
}
