package com.example.laba.controller;

import com.example.laba.Resources.Counter;
import com.example.laba.Service.CounterThread;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CounterController {
    @Autowired
    private CounterThread counter;

    @GetMapping("/counter")
    public Integer getCounter(){
        return counter.getCounter();
    }
    @GetMapping("/counter/increment")
    public void incrementCounter(){
        counter.run();
    }
}
