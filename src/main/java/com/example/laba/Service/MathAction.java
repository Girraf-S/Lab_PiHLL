package com.example.laba.Service;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.IllegalFormatException;

public class MathAction {
    private int x;

    private int y;

    private char mode;
    private String result;

    public MathAction(int x, int y, char mode){
        this.x=x;
        this.y=y;
        this.mode=mode;
    }
    public MathAction(Params params){
        try {
            this.x = Integer.parseInt(params.getX());
        }catch (NumberFormatException e){
            throw new NumberFormatException("неверный параметр x");
        }
        try {
            this.y = Integer.parseInt(params.getY());
        }catch (NumberFormatException e){
            throw new NumberFormatException("неверный параметр y");
        }
        this.mode=params.getMode().toCharArray()[0];
        if(mode!='/'&&
                mode!='*'&&
                mode!='+'&&
                mode!='-') throw new IllegalArgumentException("неверная опция mode");
    }

    public String getResult() {
        switch (mode){
            case '-': result=String.valueOf(x-y);break;
            case '+': result=String.valueOf(x+y);break;
            case '*': result=String.valueOf(x*y);break;
            case '/':
                if(y==0) {
                    result = "Деление на ноль запрещено законом РБ";
                    throw new IllegalArgumentException("Деление на ноль... серьезно...");
                }
                else {
                    result = String.valueOf(x / y);
                    break;
                }
            default:
                result="no option " +String.valueOf(mode);
        }
        return result;
    }

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