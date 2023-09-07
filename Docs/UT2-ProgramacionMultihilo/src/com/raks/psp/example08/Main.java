package com.raks.psp.example08;

public class Main {
    public static void main(String[] args) {
        var container = new Container();
        var consumer  = new Consumer(container);
        var producer  = new Producer(container);
        producer.start();
        consumer.start();
    }
}
