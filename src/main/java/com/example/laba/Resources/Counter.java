package com.example.laba.Resources;

import org.springframework.stereotype.Service;

@Service
public class Counter {
    private Integer counter = 0;
    public int increment(){
        counter++;
        return counter;
    }
    public int getCounter(){
        return counter;
    }

}
