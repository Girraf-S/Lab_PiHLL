package com.example.laba.Service;

import com.example.laba.models.ParamsModel;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;

class MathServiceTest {
    @Mock
    MathService mathService;
    @Mock
    ParamsModel paramsModel;

    @BeforeAll
    void init(){
        paramsModel.setMode('+');
        paramsModel.setX(2.2);
        paramsModel.setY(4.2);
    }
    @Test
    void calcResult() {
        mathService.getResult(paramsModel);
    }

    @Test
    void getResult() {
    }
}