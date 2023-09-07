package cadena;

import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;

public class Packager extends Thread {
    private final int             _type;
    private final int[]           _chain;
    private final Semaphore       _dropperSemaphore;
    private final Lock            _packagerLock;
    private final PackagesCounter _packagesCounter;
    private final Random          _random;

    public Packager(int type, int[] chain, Semaphore dropperSemaphore, Lock packagerLock, PackagesCounter packagesCounter) {
        _type             = type;
        _chain            = chain;
        _dropperSemaphore = dropperSemaphore;
        _packagerLock     = packagerLock;
        _packagesCounter  = packagesCounter;
        _random           = new Random();
    }

    @Override
    public void run() {
        while (true)
            try {
                _packagerLock.lockInterruptibly();
                int position = 0;
                while (position < _chain.length && _chain[position] != _type) position++;
                if (position < _chain.length) {
                    System.out.printf("Type %s robot packaging item at position %d%n", _type, position);
                    _chain[position] = 0;
                    _dropperSemaphore.release();
                    Thread.sleep(_random.nextInt(1000));
                    _packagesCounter.increase();
                }
            } catch (InterruptedException e) {
                System.out.printf("Packager %d done%n", _type);
                break;
            }
    }
}
