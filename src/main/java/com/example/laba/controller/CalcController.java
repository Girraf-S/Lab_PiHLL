package com.example.laba.controller;

import com.example.laba.Calc;
import com.example.laba.Service.MathAction;
import com.example.laba.Service.Params;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
@Validated
public class CalcController {
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
    public @NotNull ResponseEntity postController(@RequestBody Params params) {
        MathAction action;
        try {
             action = new MathAction(params);
        }catch (NumberFormatException e){
            return ResponseEntity.ok(e.getMessage());
        }catch (IllegalArgumentException e){
            return ResponseEntity.ok(e.getMessage());
        }
        String result=null;
        try{
            result=action.getResult();
        } catch (IllegalArgumentException e){
            return ResponseEntity.ok(e.getMessage());
        }
        return ResponseEntity.ok(result);
    }
}
