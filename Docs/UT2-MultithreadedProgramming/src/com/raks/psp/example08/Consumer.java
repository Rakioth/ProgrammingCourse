package com.raks.psp.example08;

public class Consumer extends Thread {

    private final Container _container;

    public Consumer(Container container) {
        _container = container;
    }

    @Override
    public void run() {
        int value;
        for (int i = 0; i < 10; i++) {
            value = _container.get();
            System.out.printf("Consumer: container.get(%d)%n", value);
        }
    }

}