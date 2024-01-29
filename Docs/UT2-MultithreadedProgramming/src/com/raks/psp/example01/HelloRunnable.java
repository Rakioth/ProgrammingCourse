package com.raks.psp.example01;

public class HelloRunnable implements Runnable {

    public static void main(String[] args) throws InterruptedException {
        Thread helloThread1 = new Thread(new HelloRunnable());
        helloThread1.start();
        Thread helloThread2 = new Thread(new HelloRunnable());
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