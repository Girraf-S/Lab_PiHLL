package com.example.laba.Service;

import com.example.laba.Cash.Cache;
import com.example.laba.models.ParamsModel;
import com.example.laba.models.ResultModel;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

class DataBaseServiceTest {
    @Mock
    private ParamsModel paramsModel;
    @Mock
    private ResultModel resultsModel;
    @Mock
    private DataBaseService dataBaseService;
    @InjectMocks
    MathService mathService;
    @Mock
    CounterThread counterThread;

    @BeforeAll
    void init(){
        paramsModel.setMode('+');
        paramsModel.setX(2.2);
        paramsModel.setY(4.2);
    }
    @Test
    void put() {
        resultsModel= mathService.getResult(paramsModel);
        dataBaseService.put(paramsModel, resultsModel);
    }
    @Test
    void select(){
        dataBaseService.select();
    }
    @Test
    @AfterAll
    void drop(){
        dataBaseService.drop();
        select();
    }
    @Test
    void counterRun(){
        for (int i = 0; i < 100; i++) {
            counterThread.run();
        }
        System.out.println(counterThread.getCounter());
    }
}