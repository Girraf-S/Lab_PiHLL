package com.example.laba.controller;

import com.example.laba.Cash.Cache;
import com.example.laba.models.ParamsModel;
import com.example.laba.models.ShowModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CacheController {
    @Autowired
    private Cache cache;
    @GetMapping("/cache/show")
    public List<ShowModel> showCache(){
        cache.update();
        return cache.showCache();
    }
}
