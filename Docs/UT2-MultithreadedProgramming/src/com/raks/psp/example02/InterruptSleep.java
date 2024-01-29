package com.raks.psp.example02;

public class InterruptSleep {

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            while (true) {
                System.out.println("Running");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    System.out.println("Interrupted");
                    break;
                }
            }
        });

        thread.start();
        Thread.sleep(4000);
        thread.interrupt();
    }

}