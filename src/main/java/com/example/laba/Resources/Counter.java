package com.example.laba.Resources;

public class Counter {
    private static Integer counter = 0;
    public static int increment(){
        counter++;
        return counter;
    }
    public static int getCounter(){
        return counter;
    }

}
