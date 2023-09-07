package com.danielmunoz.psp.sincronizacion;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;
import java.util.stream.IntStream;

public class HorseRace extends Thread {
    private final int         _maxRaces;
    private final Semaphore   _semaphore;
    private final List<Horse> _horses;

    public HorseRace(int maxHorses, int maxTime, int maxRaces) {
        _maxRaces  = maxRaces;
        _semaphore = new Semaphore(0, true);
        _horses    = new ArrayList<>(maxHorses);
        for (int i = 0; i < maxHorses; i++)
             _horses.add(new Horse(_semaphore, maxTime));
    }

    @Override
    public void run() {
        _horses.forEach(Thread::start);
        for (int i = 0; i < _maxRaces; i++)
            try {
                _semaphore.acquire(_horses.size());
                System.out.printf("Race %d results%n", i);
                IntStream.range(0, _horses.size())
                         .forEach(index -> System.out.printf("horse %d took %s seconds%n", index, _horses.get(index)));
                _horses.forEach(Horse::restart);
            } catch (InterruptedException e) {
                break;
            }

        for (Horse horse : _horses)
            try {
                horse.interrupt();
                horse.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        System.out.println("HorseRace done");
    }
}
