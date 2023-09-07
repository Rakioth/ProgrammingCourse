package com.raks.psp.example02;

public class InterruptPoll {
    public static void main(String[] args) throws InterruptedException {
        var thread = new Thread(() -> {
            while (true) {
                int sum = 0;
                for (int i = 0; i < Integer.MAX_VALUE; i++) sum += i;
                System.out.println(sum);
                if (Thread.interrupted()) {
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
