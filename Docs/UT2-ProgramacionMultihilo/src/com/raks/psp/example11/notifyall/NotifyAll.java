package com.raks.psp.example11.notifyall;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class NotifyAll {

    public static void main(String[] args) throws InterruptedException {
        ReentrantLock lock      = new ReentrantLock();
        Condition     condition = lock.newCondition();

        Runnable runnable = () -> {
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
        };

        int          numThreads = 10;
        List<Thread> threads    = new ArrayList<>();
        for (int i = 0; i < numThreads; i++) {
            Thread thread = new Thread(runnable);
            thread.start();
            threads.add(thread);
        }

        Thread.sleep(1000);
        lock.lock();
        condition.signalAll();
        // IMPORTANT NOTE
        // Comment the next line and none of the threads will continue from await, since they need to reacquire the lock which is currently owned by the main thread.
        lock.unlock();
        threads.forEach(thread -> {
            try {
                thread.interrupt();
                thread.join();
            } catch (InterruptedException e) {
                System.err.println(e);
            }
        });
    }

}