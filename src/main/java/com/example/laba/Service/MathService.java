package com.example.laba.Service;

import com.example.laba.Cash.Cache;
import com.example.laba.Exception.DivisionAtNullException;
import com.example.laba.Exception.FailArgumentsException;
import com.example.laba.controller.ActionController;
import com.example.laba.models.MinMiddleMaxModel;
import com.example.laba.models.ParamsModel;
import com.example.laba.models.ResultModel;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;


@AllArgsConstructor
@Service
public class MathService {
    @Autowired
    private Cache cache;
    @Autowired
    private DataBaseService dataBaseService;
   // @Autowired
   // private CounterThread counter;
    private static Logger logger = LoggerFactory.getLogger(ActionController.class);

    private void checkParams(ParamsModel paramsModel) throws DivisionAtNullException, FailArgumentsException {
        if (paramsModel.getY() == 0 && paramsModel.getMode() == '/')
            throw new DivisionAtNullException("Деление на ноль запрещено законом РБ");
    }

    public ResultModel calcResult(ParamsModel paramsModel) throws DivisionAtNullException, FailArgumentsException {
        try {
            checkParams(paramsModel);
        } catch (DivisionAtNullException e) {
            throw e;
        } catch (FailArgumentsException e) {
            throw e;
        }
        if (!cache.containsKey(paramsModel)) {
            Double result = null;
            switch (paramsModel.getMode()) {
                case '+':
                    result = paramsModel.getX() + paramsModel.getY();
                    break;
                case '-':
                    result = paramsModel.getX() - paramsModel.getY();
                    break;
                case '*':
                    result = paramsModel.getX() * paramsModel.getY();
                    break;
                case '/':
                    result = paramsModel.getX() / paramsModel.getY();
                    break;
            }
            return new ResultModel(result);
        } else return new ResultModel(cache.get(paramsModel));
    }
    public ResultModel getResult(ParamsModel p) {
        //counter.run();
        try {
            return calcResult(p);
        } catch (DivisionAtNullException e) {
            logger.error(e.getMessage());
        } catch (FailArgumentsException e) {
            logger.error(e.getMessage());
        }
        return new ResultModel();
    }

    public  List<ResultModel> getListResult(List<ParamsModel> actionModelList) {
        List<ResultModel> resultList = actionModelList.stream().map(r -> {
            return getResult(r);
        }).collect(Collectors.toList());
        return resultList;
    }
    public MinMiddleMaxModel getMMM(List<ParamsModel> list) {
        MinMiddleMaxModel minMiddleMaxModel = new MinMiddleMaxModel();
        List<Double> result = getListResult(list).stream().map(r->r.getResult()).sorted().collect(Collectors.toList());
        minMiddleMaxModel.setMin(result.get(0));
        minMiddleMaxModel.setMax(result.get(result.size() - 1));
        minMiddleMaxModel.setMiddle(result.stream().
                reduce(0.0, (x, y) -> x + y) / result.size());
        System.out.println( minMiddleMaxModel.getMiddle());
        return minMiddleMaxModel;
    }
    public void async(ParamsModel paramsModel){
        int id = dataBaseService.async(paramsModel);
        CompletableFuture.runAsync(()->{
            try {
                Thread.sleep(10000);
                dataBaseService.asyncResult(getResult(paramsModel), id);
            } catch (InterruptedException e) {
                logger.error(e.getMessage());
            }
        });
    }
}
