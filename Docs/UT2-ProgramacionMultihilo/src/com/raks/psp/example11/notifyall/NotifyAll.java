package com.raks.psp.example11.notifyall;

import java.util.ArrayList;
import java.util.concurrent.locks.ReentrantLock;

public class NotifyAll {
    public static void main(String[] args) throws InterruptedException {
        var lock      = new ReentrantLock();
        var condition = lock.newCondition();
        var runnable = new Runnable() {
            @Override
            public void run() {
                try {
                    lock.lock();
                    System.out.printf("Thread %s acquired lock and going to await %n", Thread.currentThread());
                    condition.await();
                    System.out.printf("Thread %s just woke up from await %n", Thread.currentThread());
                } catch (InterruptedException e) {
                    System.out.printf("Thread %s interrupted %n", Thread.currentThread());
                } finally {
                    lock.unlock();
                }
            }
        };

        var numThreads = 10;
        var threads    = new ArrayList<Thread>();
        for (int i = 0; i < numThreads; i++) {
            Thread thread = new Thread(runnable);
            thread.start();
            threads.add(thread);
        }

        Thread.sleep(1000);
        lock.lock();
        condition.signalAll();
        // IMPORTANT NOTE
        // comment the next line and none of the threads will continue from await, since they need to reacquire the lock which is currently owned by the main thread.
        lock.unlock();
        threads.forEach(it -> {
            try {
                it.interrupt();
                it.join();
            } catch (InterruptedException e) {
                System.err.println(e);
            }
        });
    }
}
