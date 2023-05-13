package com.example.laba.Cash;

import com.example.laba.controller.CalcController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class Cache {
    private static Map<String, String> cash = new ConcurrentHashMap<>();
    private static Logger logger = LoggerFactory.getLogger(CalcController.class);

    public void put(String equation, String result) {
        cash.put(equation, result);
    }

    public String get(String equation) {
        return cash.get(equation);
    }

    public boolean containsKey(String equation) {
        return cash.containsKey(equation);
    }
    public Iterator<String> getKeys(){
        return cash.keySet().iterator();
    }

    public Iterator<String> getValues(){
        return cash.values().iterator();
    }
    public List<String> showCash(){
        Iterator<String> keyIterator=cash.keySet().iterator();
        Iterator<String> valueIterator=cash.values().iterator();
        List<String> list = new ArrayList<>();
        for(String key: cash.keySet()){
            list.add(key+" = "+cash.get(key));
        }
        logger.info("вывод кеша");
        return list;
    }
}
