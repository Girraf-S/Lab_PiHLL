package com.example.laba;

import com.example.laba.Service.MathAction;
import com.example.laba.Service.Params;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class MathActionTest {
    static MathAction mathAction;
    @BeforeAll
    public static void init(){
//        int x, y;
//        char mode;
//        try(Scanner scanner=new Scanner(System.in)){
//            x=scanner.nextInt();
//            y=scanner.nextInt();
//            mode=scanner.nextLine().toCharArray()[0];
//        }
        mathAction=new MathAction(5,0,'/');
    }
    @Test
    void getResult() {
        try {
            System.out.println(mathAction.getResult());
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    @AfterAll
    @Test
    static void checkInput(){
        mathAction.setX(104);
        try{
            mathAction.getResult();
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    @Test
    void parse(){
        Params params=new Params();


    }
}