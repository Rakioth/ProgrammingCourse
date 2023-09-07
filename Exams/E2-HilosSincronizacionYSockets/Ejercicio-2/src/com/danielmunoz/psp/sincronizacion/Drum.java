package com.danielmunoz.psp.sincronizacion;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Drum extends Thread {
    private final Semaphore _slotMachineSemaphore;
    private final Lock      _lock;
    private final int       _maxTime;
    private final int       _maxValue;
    private       int       _value;

    public Drum(Semaphore slotMachineSemaphore, int maxTime, int maxValue) {
        _slotMachineSemaphore = slotMachineSemaphore;
        _maxTime              = maxTime;
        _maxValue             = maxValue;
        _lock                 = new ReentrantLock();
    }

    public void restart() {
        if (_lock.tryLock()) _lock.unlock();
    }

    @Override
    public void run() {
        while (true)
            try {
                _lock.lockInterruptibly();
                Thread.sleep((long) (Math.random() * _maxTime));
                _value = (int) (Math.random() * _maxValue);
                _slotMachineSemaphore.release();
            } catch (InterruptedException e) {
                System.out.println("Drum done");
                break;
            }
    }

    @Override
    public String toString() {
        return String.valueOf(_value);
    }
}
