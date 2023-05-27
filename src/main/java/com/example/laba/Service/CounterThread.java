package com.example.laba.Service;

import com.example.laba.Resources.Counter;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.Semaphore;
@Service
public class CounterThread implements Runnable {
    Semaphore semaphore = new Semaphore(1);
    @Autowired
    Counter counter;

    @SneakyThrows
    @Override
    public void run() {
        semaphore.acquire();
        counter.increment();
        semaphore.release();
    }

    public int getCounter() {
        return counter.getCounter();
    }
}
