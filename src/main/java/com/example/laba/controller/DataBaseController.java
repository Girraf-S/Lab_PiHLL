package com.example.laba.controller;

import com.example.laba.Service.DataBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DataBaseController {
    @Autowired
    private DataBaseService dataBaseService;
    @GetMapping("database/drop")
    public String drop(){
        dataBaseService.drop();
        return "Dropped.";
    }
    @GetMapping("database/truncate")
    public String truncate(){
        dataBaseService.truncate();
        return "Dropped.";
    }
}
