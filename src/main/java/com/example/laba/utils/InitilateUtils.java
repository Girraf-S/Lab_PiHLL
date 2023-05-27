package com.example.laba.utils;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import java.sql.Connection;

@Service
public class InitilateUtils implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        System.out.println("run"); //проверим что это работает
    }

}
