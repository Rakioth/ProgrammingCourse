package com.raks.psp.example10.lock;

import java.util.ArrayList;
import java.util.List;

public class DiningPhilosophers {

    private static final int NUM_FORKS = 3;

    private final List<Philosopher> _philosophers;

    public DiningPhilosophers() {
        List<Fork> forks = new ArrayList<>();
        for (int fork = 0; fork < NUM_FORKS; fork++)
             forks.add(new Fork());

        _philosophers = new ArrayList<>();
        for (int num = 0; num < NUM_FORKS; num++)
             _philosophers.add(new Philosopher(num, forks.get(num), forks.get((num + 1) % NUM_FORKS)));
    }

    public void dine() {
        _philosophers.forEach(Thread::start);
    }

}