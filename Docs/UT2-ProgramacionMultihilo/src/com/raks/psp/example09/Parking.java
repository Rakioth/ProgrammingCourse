package com.raks.psp.example09;

public class Parking {

    private static final int MIN_SPOTS = 2;

    private int _availableSpots;

    public Parking(int maxCapacity) {
        if (maxCapacity <= MIN_SPOTS) maxCapacity = MIN_SPOTS;
        _availableSpots = maxCapacity;
    }

    public synchronized void enter(Car car) throws InterruptedException {
        while (_availableSpots == 0) {
            System.out.printf("Car %s is waiting%n", car);
            wait();
        }
        _availableSpots--;
        System.out.printf("Car %s is parked. Available spots %d%n", car, _availableSpots);
    }

    public synchronized void leave(Car car) {
        _availableSpots++;
        System.out.printf("Car %s left. Available spots %d%n", car, _availableSpots);
        notifyAll();
    }

}