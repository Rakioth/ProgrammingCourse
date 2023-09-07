package com.raks.psp.example05;

public class Counter {
    private static final int MAX_COUNT = 1000;

    private int c = 0;

    public synchronized void increment() {
        c++;
    }

    public synchronized void decrement() {
        c--;
    }

    public synchronized int value() {
        return c;
    }

    public static void main(String[] args) throws InterruptedException {
        var counter = new Counter();
        var incrementer = new Thread(() -> {
            for (int i = 0; i < MAX_COUNT; i++) {
                counter.increment();
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        var decrementer = new Thread(() -> {
            for (int i = 0; i < MAX_COUNT; i++) {
                counter.decrement();
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        incrementer.start();
        decrementer.start();
        incrementer.join();
        decrementer.join();
        System.out.println(counter.value());
    }
}
