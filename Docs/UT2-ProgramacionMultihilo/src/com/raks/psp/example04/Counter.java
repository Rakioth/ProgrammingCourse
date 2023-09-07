package com.raks.psp.example04;

public class Counter {
    private static final int MAX_COUNT = 1000;

    private int c = 0;

    public void increment() {
        c++;
    }

    public void decrement() {
        c--;
    }

    public int value() {
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
