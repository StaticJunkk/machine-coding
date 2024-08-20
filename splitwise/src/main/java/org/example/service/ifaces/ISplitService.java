package org.example.service.ifaces;

import java.util.HashMap;

public interface ISplitService {

    HashMap<String, Double> split(HashMap<String, Double> expenseList, Double Amount);
}
