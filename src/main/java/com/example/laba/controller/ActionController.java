package com.example.laba.controller;

import com.example.laba.Cash.Cache;
import com.example.laba.Service.CounterThread;
import com.example.laba.Service.MathService;
import com.example.laba.models.MinMiddleMaxModel;
import com.example.laba.models.ParamsModel;
import com.example.laba.models.ResultModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ActionController {
    @Autowired
    private MathService mathService;
    @Autowired
    private CounterThread counter;
    @Autowired
    private Cache cache;

    @GetMapping("/calculator")
    public ResultModel getResult(@RequestBody ParamsModel paramsModel){
        counter.run();
        ResultModel resultModel = mathService.getResult(paramsModel);
        cache.put(paramsModel, resultModel.getResult());
        return resultModel;
    }
    @PostMapping("/list")
    public List<ResultModel> getList(@RequestBody List<ParamsModel> paramsModels){
        counter.run();
        return  mathService.getListResult(paramsModels);
    }
    @PostMapping("/list/six")
    public MinMiddleMaxModel getMinMiddleMax(@RequestBody List<ParamsModel> paramsModels){
        counter.run();
        return mathService.getMMM(paramsModels);
    }
    @PostMapping("/calculator/async")
    public void getAsync(@RequestBody ParamsModel paramsModel){
         mathService.async(paramsModel);
    }
}
