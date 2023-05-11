package com.example.laba.controller;

import com.example.laba.Calc;
import com.example.laba.Cash.Cash;
import com.example.laba.Resources.Counter;
import com.example.laba.Service.CounterThread;
import com.example.laba.Service.MathAction;
import com.example.laba.Service.Params;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.*;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@RestController
@Validated
public class CalcController {
    CounterThread counter = new CounterThread();
    Cash cash = new Cash();

    @GetMapping("/calculate")
    public Calc calc(@RequestParam("x") String x,
                     @RequestParam("y") @Valid String y,
                     @RequestParam("mode") String mode) {
        char temp = mode.toCharArray()[0];
        String result = null;
        MathAction mathAction = new MathAction(Integer.parseInt(x), Integer.parseInt(y), temp);
        result = mathAction.getResult();
        return new Calc(mathAction.getX(), mathAction.getY(), mathAction.getMode(), result);
    }

    @GetMapping("/request")
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public @NotNull ResponseEntity getController(@RequestBody Params params) {
        MathAction action;
        counter.run();
        String result = "количество запусков: " + Counter.getCounter();
        try {
            action = new MathAction(params);
        } catch (NumberFormatException e) {//неверный параметр x или y
            return ResponseEntity.ok(String.format("%s%s", e.getMessage(), result));
        } catch (IllegalArgumentException e) {//неверная опция mode
            return ResponseEntity.ok(String.format("%s%s", e.getMessage(), result));
        }
        try {
//            List<String> bulkResult= Arrays.asList(params.getX(), params.getY(), params.getMode());
//            Comparator<String> cmp = (x, y)-> Integer.parseInt((
//                    action.getMode()=='+'? String.valueOf(Integer.parseInt(x)+Integer.parseInt(y)):
//                    action.getMode()=='-'? String.valueOf(Integer.parseInt(x)-Integer.parseInt(y)):
//                    action.getMode()=='*'? String.valueOf(Integer.parseInt(x)*Integer.parseInt(y)):
//                    String.valueOf(Integer.parseInt(x)/Integer.parseInt(y))));
//            String res;
            String resultAction;
            String equation = action.toEquation();
            String haveCash = "cash";
            if (cash.containsKey(equation)) resultAction = cash.get(equation);
            else {
                resultAction = action.getResult();
                cash.put(equation, resultAction);
                haveCash = null;
            }
            result = String.format("%s = %s\n%s\n%s", equation, resultAction, result, haveCash);
        } catch (IllegalArgumentException e) {//деление на ноль
            return ResponseEntity.ok(String.format("%s%s", e.getMessage(), result));
        }
        return ResponseEntity.ok(result);
    }
    @PostMapping("/request")
    public @NotNull ResponseEntity postController(@RequestBody Params params){
        MathAction action;
        counter.run();
        String result = "количество запусков: " + Counter.getCounter();
        try {
            action = new MathAction(params);
        } catch (NumberFormatException e) {//неверный параметр x или y
            return ResponseEntity.ok(String.format("%s%s", e.getMessage(), result));
        } catch (IllegalArgumentException e) {//неверная опция mode
            return ResponseEntity.ok(String.format("%s%s", e.getMessage(), result));
        }
        try {
            List<String> list = new ArrayList<>();//Arrays.asList(params.getX(), params.getY(), params.getMode());
            Collections.addAll(list, params.getX(), params.getY(), params.getMode());

//            String res = (x, y) -> Integer.parseInt((
//                    action.getMode() == '+' ? String.valueOf(Integer.parseInt(x) + Integer.parseInt(y)) :
//                            action.getMode() == '-' ? String.valueOf(Integer.parseInt(x) - Integer.parseInt(y)) :
//                                    action.getMode() == '*' ? String.valueOf(Integer.parseInt(x) * Integer.parseInt(y)) :
//                                    String.valueOf(Integer.parseInt(x) / Integer.parseInt(y))));
            //String res = Stream.of(action).map(MathAction::getResult).toString();
            String resultAction;
            String equation = action.toEquation();
            String haveCash = "cash";
            if (cash.containsKey(equation)) resultAction = cash.get(equation);
            else {
                resultAction = action.getResult();
                cash.put(equation, resultAction);
                haveCash = null;
            }
            result = String.format("%s = %s\n%s\n%s", equation, resultAction, result, haveCash);
        } catch (IllegalArgumentException e) {//деление на ноль
            return ResponseEntity.ok(String.format("%s%s", e.getMessage(), result));
        }
        return ResponseEntity.ok(result);
    }

}
