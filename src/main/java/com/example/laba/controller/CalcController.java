package com.example.laba.controller;

import com.example.laba.Calc;
import com.example.laba.Cash.Cache;
import com.example.laba.Resources.Counter;
import com.example.laba.Service.CounterThread;
import com.example.laba.Service.MathAction;
import com.example.laba.Service.Params;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.*;

@RestController
@Validated
public class CalcController {
    CounterThread counter = new CounterThread();
    Cache cache = new Cache();

    @GetMapping("/calculate")
    public Calc calc(@RequestParam("x") String x,
                     @RequestParam("y") String y,
                     @RequestParam("mode") String mode) {
        char temp = mode.toCharArray()[0];
        String result = null;
        MathAction mathAction = new MathAction(Integer.parseInt(x), Integer.parseInt(y), temp);
        result = mathAction.getResult();
        return new Calc(mathAction.getX(), mathAction.getY(), mathAction.getMode(), result);
    }

    @GetMapping("/calculator")
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public @NotNull ResponseEntity getController(@RequestBody Params params) {
        MathAction action;
        String result = "количество запусков: " + Counter.getCounter();
        try {
            action = new MathAction(params);
        } catch (NumberFormatException e) {                        //неверный параметр x или y
            return ResponseEntity.ok(String.format("%s%s", e.getMessage(), result));
        } catch (IllegalArgumentException e) {                      //неверная опция mode
            return ResponseEntity.ok(String.format("%s%s", e.getMessage(), result));
        }
        try {
            String resultAction;                             //value
            String equation = action.toEquation();          //key
            resultAction = action.getResult();
            result = String.format("%s = %s\n%s", equation, resultAction, result);
        } catch (IllegalArgumentException e) {           //деление на ноль
            return ResponseEntity.ok(String.format("%s%s", e.getMessage(), result));
        }
        return ResponseEntity.ok(result);
    }
    @GetMapping("/counter")
    public int getCounter(){
        return counter.getCounter();
    }
    @GetMapping("/counter/increment")
    public int incCounter(){
        counter.run();
        return counter.getCounter();
    }
    @GetMapping("/showCash")
    public List<String> show(){
        return cache.showCash();
    }
    @PostMapping("/calculator/list")
    public List<String> listCalculator(@RequestBody List<Params> params){
        return MathAction.getList(params);
    }

}
