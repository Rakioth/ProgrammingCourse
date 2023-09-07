package com.danielmunoz.psp.sincronizacion;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

public class SlotMachine extends Thread {
    private final int        _maxGames;
    private final Semaphore  _semaphore;
    private final List<Drum> _drums;

    public SlotMachine(int maxDrums, int maxTime, int maxValue, int maxGames) {
        _maxGames  = maxGames;
        _semaphore = new Semaphore(0, true);
        _drums     = new ArrayList<>(maxDrums);
        for (int i = 0; i < maxDrums; i++)
             _drums.add(new Drum(_semaphore, maxTime, maxValue));
    }

    @Override
    public void run() {
        _drums.forEach(Thread::start);
        for (int i = 0; i < _maxGames; i++)
            try {
                _semaphore.acquire(_drums.size());
                StringBuilder value = new StringBuilder();
                for (Drum drum : _drums)
                    value.append(drum);
                System.out.printf("Slots value %s%n", value);
                _drums.forEach(Drum::restart);
            } catch (InterruptedException e) {
                break;
            }

        for (Drum drum : _drums)
            try {
                drum.interrupt();
                drum.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        System.out.println("Slot machine done");
    }
}
