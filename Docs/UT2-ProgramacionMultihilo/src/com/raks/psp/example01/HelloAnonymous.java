package com.raks.psp.example01;

public class HelloAnonymous {
    public static void main(String[] args) throws InterruptedException {
        var t1 = new Thread(new Runnable() {
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
        });
        t1.start();

        var t2 = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                System.out.printf("Hello %d%n", i);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        t2.start();
    }
}