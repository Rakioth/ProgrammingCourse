package cadena;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;

public class Dropper extends Thread {
    private final int[]     _chain;
    private final Semaphore _slots;
    private final Lock[]    _packagersLocks;
    private final int       _packagersCount;
    private final Random    _random;

    public Dropper(int[] chain, Semaphore slots, Lock[] packagerLocks) {
        _chain          = chain;
        _slots          = slots;
        _packagersLocks = packagerLocks;
        _packagersCount = packagerLocks.length;
        _random         = new Random();
    }

    @Override
    public void run() {
        while (true)
            try {
                int type = _random.nextInt(1, _packagersCount + 1);
                Thread.sleep(_random.nextInt(500));
                _slots.acquire();
                int position = 0;
                while (position < _chain.length && _chain[position] != 0) position++;
                if (position < _chain.length) {
                    _chain[position] = type;
                    System.out.printf("Dropping type %d on position %d. %s%n", type, position, Arrays.toString(_chain));
                }
                if (_packagersLocks[type - 1].tryLock()) _packagersLocks[type - 1].unlock();
            } catch (InterruptedException e) {
                System.out.println("Dropper done");
                break;
            }
    }
}
