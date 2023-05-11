package com.example.laba.Cash;

import java.util.HashMap;
import java.util.Map;

public class Cash {
    private static Map<String, String> cash=new HashMap<>();
    public void put(String equation, String result){
        cash.put(equation, result);
    }
    public String get(String equation){
        return cash.get(equation);
    }
    public boolean containsKey(String equation){
        return cash.containsKey(equation);
    }
}
