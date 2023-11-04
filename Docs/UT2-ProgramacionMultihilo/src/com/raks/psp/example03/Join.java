package com.raks.psp.example03;

public class Join {

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                System.out.println("Running");
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    System.out.println("Interrupted");
                    break;
                }
            }
        });

        thread.start();
        thread.join();
    }

}