package com.raks.psp.example11.pingpong;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class PingPong extends Thread {
    private       String        _word;
    private final ReentrantLock _lock;
    private final Condition     _condition;

    public PingPong(String word, ReentrantLock lock, Condition condition) {
        _word      = word;
        _lock      = lock;
        _condition = condition;
    }

    public void run() {
        while (true) {
            _lock.lock();
            try {
                System.out.print(_word);
                _condition.signal();
                _condition.await();
            } catch (InterruptedException e) {
                break;
            } finally {
                _lock.unlock();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        var lock      = new ReentrantLock();
        var condition = lock.newCondition();
        var ping      = new PingPong("P", lock, condition);
        var pong      = new PingPong("p", lock, condition);
        ping.start();
        pong.start();
        Thread.sleep(10000);
        ping.interrupt();
        pong.interrupt();
        ping.join();
        pong.join();
    }
}