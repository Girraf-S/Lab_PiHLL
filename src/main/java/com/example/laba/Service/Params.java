package com.example.laba.Service;

public class Params {
    private String x;
    private String y;
    private String mode;
    private String result;
    public Params(String x, String y, String mode) {
        this.x = x;
        this.y = y;
        this.mode = mode;
        result = null;
    }

    public Params(){}
    public String getX(){
        return x;
    }

    public String getY() {
        return y;
    }

    public String getMode() {
        return mode;
    }

    public void setResult(String result) {
        this.result = result;
    }
    @Override
    public String toString() {
        return String.format("%s %c %s = %s", x, mode.toCharArray()[0], y, result);
    }
}
