package com.raks.psp.synchronization;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Horse extends Thread {

    private final Semaphore _horseRaceSemaphore;
    private final Lock      _lock;
    private final int       _maxTime;
    private       float     _time;

    public Horse(Semaphore horseRaceSemaphore, int maxTime) {
        _horseRaceSemaphore = horseRaceSemaphore;
        _maxTime            = maxTime;
        _lock               = new ReentrantLock();
    }

    public void restart() {
        if (_lock.tryLock()) _lock.unlock();
    }

    @Override
    public void run() {
        while (true)
            try {
                _lock.lockInterruptibly();
                long sleep = 1000 + (long) (Math.random() * _maxTime);
                Thread.sleep(sleep);
                _time = sleep / (float) 1000;
                _horseRaceSemaphore.release();
            } catch (InterruptedException e) {
                System.out.println("Horse done");
                break;
            }
    }

    @Override
    public String toString() {
        return String.format("%.2f", _time);
    }

}