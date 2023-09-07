package com.raks.psp.example11.boundedbuffer;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

class BoundedBuffer<E> {
    final ReentrantLock lock     = new ReentrantLock();
    final Condition     notFull  = lock.newCondition();
    final Condition     notEmpty = lock.newCondition();
    final Object[]      items    = new Object[10];
    int putptr, takeptr, count;

    public void put(E x) throws InterruptedException {
        lock.lock();
        try {
            while (count == items.length) {
                System.out.println("Full");
                notFull.await();
            }
            items[putptr] = x;
            if (++putptr == items.length) putptr = 0;
            ++count;
            System.out.println(count);
            notEmpty.signal();
        } finally {
            lock.unlock();
        }
    }

    public E take() throws InterruptedException {
        lock.lock();
        try {
            while (count == 0) {
                System.out.println("Empty");
                notEmpty.await();
            }
            E x = (E) items[takeptr];
            if (++takeptr == items.length)
                takeptr = 0;
            --count;
            System.out.println(count);
            notFull.signal();
            return x;
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        var stringBoundedBuffer = new BoundedBuffer<String>();
        var putter = new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        stringBoundedBuffer.put("yo");
                        Thread.sleep((long) (Math.random() * 1000));
                    } catch (InterruptedException e) {
                        break;
                    }
                }
            }
        };
        var taker = new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        stringBoundedBuffer.take();
                        Thread.sleep((long) (Math.random() * 1000));
                    } catch (InterruptedException e) {
                        break;
                    }
                }
            }
        };

        var putter1 = new Thread(putter);
        var putter2 = new Thread(putter);
        var taker1  = new Thread(taker);
        putter1.start();
        putter2.start();
        taker1.start();
        Thread.sleep(10000);
        putter1.interrupt();
        putter2.interrupt();
        taker1.interrupt();
        putter1.join();
        putter2.join();
        taker1.join();
    }
}
