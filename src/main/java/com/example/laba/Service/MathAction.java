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
    private void rightArgument(){
        boolean condition =true;
        String xMessage="";
        String yMessage="";
        String modeMessage="";
        char[] checkX=String.valueOf(this.x).toCharArray();
        char[] checkY=String.valueOf(this.y).toCharArray();
        for (int i = 0; i < checkX.length; i++) {
            if (checkX[i] >= '0' && checkX[i] <= '9') {
                continue;
            } else {
                xMessage = "неверный параметр x\n";
                break;
            }
        }
        for (int i = 0; i < checkY.length; i++) {
            if (checkY[i] >= '0' && checkY[i] <= '9')
                continue;
            else {
                yMessage = "неверный параметр y\n";
                break;
            }
        }
        if(mode!='/'&&
                mode!='*'&&
                mode!='+'&&
                mode!='-')
            modeMessage="неверная опция mode";
        if(xMessage.equals("")&&
                yMessage.equals("")&&
                modeMessage.equals("")) {
            return;
        }
        else {
            throw new IllegalArgumentException(xMessage+yMessage+modeMessage);
        }
    }
    public String getResult() {
        try {
            rightArgument();
        }
        catch (IllegalArgumentException e){
            throw e;
        }
        switch (mode){
            case '-': result=String.valueOf(x-y);break;
            case '+': result=String.valueOf(x+y);break;
            case '*': result=String.valueOf(x*y);break;
            case '/':
                if(y==0) {
                    result = "Деление на ноль запрещено законом РБ";
                    throw new IllegalArgumentException("Деление на ноль... серьезно...");
                }
                else
                    result=String.valueOf(x/y);break;
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
