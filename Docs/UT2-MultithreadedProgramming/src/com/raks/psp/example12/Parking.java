package com.raks.psp.example12;

import java.util.concurrent.Semaphore;

public class Parking {

    private static final int MIN_SPOTS = 2;

    private final Semaphore _availableSpots;

    public Parking(int maxCapacity) {
        if (maxCapacity <= MIN_SPOTS) maxCapacity = MIN_SPOTS;
        _availableSpots = new Semaphore(maxCapacity);
    }

    public void enter(Car car) throws InterruptedException {
        _availableSpots.acquire();
        System.out.printf("Car %s is parked. Available spots %d%n", car, _availableSpots.availablePermits());
    }

    public void leave(Car car) {
        _availableSpots.release();
        System.out.printf("Car %s left. Available spots %d%n", car, _availableSpots.availablePermits());
    }

}