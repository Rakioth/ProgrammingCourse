package com.raks.psp.example08;

public class Main {

    public static void main(String[] args) {
        Container container = new Container();
        Consumer  consumer  = new Consumer(container);
        Producer  producer  = new Producer(container);

        producer.start();
        consumer.start();
    }

}