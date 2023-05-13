package com.example.laba.Service;
import com.example.laba.Cash.Cache;
import com.example.laba.controller.CalcController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.*;
import java.util.stream.Collectors;

public class MathAction {
    private int x;

    private int y;

    private char mode;
    private String result;
    Cache cache = new Cache();
    private static Logger logger = LoggerFactory.getLogger(CalcController.class);

    @Autowired
    public MathAction(Cache cache) {
        logger.info("dependency injection ");
        this.cache = cache;
    }

    public MathAction(int x, int y, char mode) {
        this.x = x;
        this.y = y;
        this.mode = mode;
    }


    public MathAction(Params params) {
        try {
            this.x = Integer.parseInt(params.getX());
        } catch (NumberFormatException e) {
            logger.error("Неверный параметр x");
            throw new NumberFormatException("Неверный параметр x\n");
        }
        try {
            this.y = Integer.parseInt(params.getY());
        } catch (NumberFormatException e) {
            logger.error("Неверный параметр у");
            throw new NumberFormatException("Неверный параметр y\n");
        }
        this.mode = params.getMode().toCharArray()[0];
        if (mode != '/' &&
                mode != '*' &&
                mode != '+' &&
                mode != '-') {
            logger.error("Неверный параметр mode");
            throw new IllegalArgumentException("неверная опция mode\n");
        }
    }

    public String getResult() {
        //Cache cache =new Cache();
        String equation = toEquation();
        if ((result = cache.get(equation)) == null) {
            switch (mode) {
                case '-':
                    result = String.valueOf(x - y);
                    cache.put(equation, result);
                    break;
                case '+':
                    result = String.valueOf(x + y);
                    cache.put(equation, result);
                    break;
                case '*':
                    result = String.valueOf(x * y);
                    cache.put(equation, result);
                    break;
                case '/':
                    if (y == 0) {
                        logger.info("Нарушение конвенкции о запрете деления на ноль");
                        result = "Деление на ноль запрещено законом РБ\n";
                        throw new IllegalArgumentException(result);
                    } else {
                        result = String.format("%.3f", Double.valueOf(x) / Double.valueOf(y));
                        cache.put(equation, result);
                        break;
                    }
            }
        } else logger.info("результат взят из кэша");
        return result;
    }

    public String toEquation() {
        String equation = "%d %c %d";
        return String.format(equation, x, mode, y);
    }

//    public List<Params> getResultForList(Params params) {
//        list.add(params);
//        List<Params>newList = list.stream().map(p -> {
//            try {
//                this.x = Integer.parseInt(params.getX());
//            } catch (NumberFormatException e) {
//                logger.error("Неверный параметр x");
//                throw new NumberFormatException("Неверный параметр x\n");
//            }
//            try {
//                this.y = Integer.parseInt(params.getY());
//            } catch (NumberFormatException e) {
//                logger.error("Неверный параметр у");
//                throw new NumberFormatException("Неверный параметр y\n");
//            }
//            this.mode = params.getMode().toCharArray()[0];
//            if (mode != '/' &&
//                    mode != '*' &&
//                    mode != '+' &&
//                    mode != '-') {
//                logger.error("Неверный параметр mode");
//                throw new IllegalArgumentException("неверная опция mode\n");
//            }
//        });
//
//        return list;
//    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public char getMode() {
        return mode;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setMode(char mode) {
        this.mode = mode;
    }
}