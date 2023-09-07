package com.raks.psp.example01;

public class HelloThread extends Thread {
    public static void main(String[] args) throws InterruptedException {
        var helloThread1 = new HelloThread();
        helloThread1.start();
        var helloThread2 = new HelloThread();
        helloThread2.start();
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.printf("Hello %d%n", i);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
