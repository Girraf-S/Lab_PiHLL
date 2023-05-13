package com.example.laba.Service;

import com.example.laba.Resources.Counter;
import lombok.SneakyThrows;

import java.util.concurrent.Semaphore;

public class CounterThread implements Runnable {
    Semaphore semaphore = new Semaphore(1);

    @SneakyThrows
    @Override
    public void run() {
        semaphore.acquire();
        Counter.increment();
        semaphore.release();
    }

    public int getCounter() {
        return Counter.getCounter();
    }
}
