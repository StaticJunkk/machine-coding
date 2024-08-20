package org.example.service.impl;

import org.example.service.ifaces.ISplitService;

import java.util.HashMap;

public class PercentageSplitService implements ISplitService {
    @Override
    public HashMap<String, Double> split(HashMap<String, Double> expenseList,  Double amount) {
        expenseList.forEach((person, percentage) -> {
            expenseList.put(person, (percentage*amount)/100 );
        });
        return expenseList;
    }
}
