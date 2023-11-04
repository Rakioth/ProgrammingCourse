package com.raks.psp.example09;

public class Car extends Thread {

    private final String  _name;
    private final Parking _parking;

    public Car(String name, Parking parking) {
        _name    = name;
        _parking = parking;
    }

    @Override
    public void run() {
        while (true)
            try {
                Thread.sleep((long) (Math.random() * 1000));
                _parking.enter(this);
                Thread.sleep((long) (Math.random() * 1000));
                _parking.leave(this);
            } catch (InterruptedException e) {
                System.out.printf("Car %s interrupted%n", this);
                break;
            }
    }

    @Override
    public String toString() {
        return _name;
    }

}