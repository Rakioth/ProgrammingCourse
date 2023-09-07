package com.raks.psp.example12;

import java.util.List;

public class Main {
    private static final int MAX_CAPACITY = 3;

    public static void main(String[] args) throws InterruptedException {
        var parking = new Parking(MAX_CAPACITY);
        var names   = List.of("Fiat", "Porsche", "Audi", "BMW");
        var cars    = names.stream().map(name -> new Car(name, parking)).toList();

        System.out.println("Main: starting cars");
        cars.forEach(Thread::start);

        System.out.println("Main: sleeping, let the cars play");
        Thread.sleep(10000);

        System.out.println("Main: interrupting cars");
        for (Car car : cars)
            car.interrupt();

        System.out.println("Main: joining cars");
        for (Car car : cars)
            car.join();

        System.out.println("Main: done");
    }
}
