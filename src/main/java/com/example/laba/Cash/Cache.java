package com.example.laba.Cash;

import com.example.laba.Service.DataBaseService;
import com.example.laba.controller.ActionController;
import com.example.laba.models.ParamsModel;
import com.example.laba.models.ResultModel;
import com.example.laba.models.ShowModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
@Service
public class Cache {
  //  private static Map<String, String> cash = new ConcurrentHashMap<>();
    private Logger logger = LoggerFactory.getLogger(ActionController.class);
    private Map<ParamsModel, Double> cache=new ConcurrentHashMap<>();
    private final DataBaseService dataBaseService;
    @Autowired
    public Cache(DataBaseService dataBaseService){
        List<ShowModel> list = dataBaseService.uploadToCache();
        for (ShowModel model:
             list) {
            cache.put(new ParamsModel(model.getX(), model.getY(), model.getMode()), model.getResult());
        }
        this.dataBaseService = dataBaseService;
    }
    public void put(ParamsModel paramsModel, Double result){
        cache.put(paramsModel, result);
        dataBaseService.put(paramsModel, new ResultModel(result));
    }
    public double get(ParamsModel paramsModel){
        return cache.get(paramsModel);
    }
    public boolean containsKey(ParamsModel equation){
        return cache.containsKey(equation);
    }
    public List<ShowModel> showCache(){
        List<ShowModel> list = new ArrayList<>();
        for(ParamsModel key: cache.keySet()){
            list.add(new ShowModel(key.getX(),key.getY(), key.getMode(), new ResultModel(cache.get(key)).getResult()));
        }
        return list;
    }
    public void update(){
        cache.clear();
        List<ShowModel> list = dataBaseService.uploadToCache();
        for (ShowModel model:
                list) {
            cache.put(new ParamsModel(model.getX(), model.getY(), model.getMode()), model.getResult());
        }
    }
}
