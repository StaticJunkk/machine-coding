package org.example.service.impl;

import org.example.service.ifaces.ISplitService;

import java.util.HashMap;

public class ExactSplitService implements ISplitService {
    @Override
    public HashMap<String, Double> split(HashMap<String, Double> expenseList,  Double Amount) {
        return expenseList;
    }
}
