package com.raks.psp.example02;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        Thread sleeper = new Thread(() -> {
            while (true) {
                System.out.println("Going to sleep for 1 second");
                try {
                    Thread.sleep(1000);
                    System.out.println("Woke up");
                    System.out.println("Now I'm wasting some CPU but not sleeping");
                    for (int i = 0; i < 1000; i++)
                         System.out.println("Wasting time");
                } catch (InterruptedException e) {
                    System.out.println("Interrupted");
                    break;
                }
            }
        });

        sleeper.start();
        Thread.sleep(2000);
        sleeper.interrupt();
        sleeper.join();
    }

}