package industrial;

import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Sensor extends Thread {
    private final Semaphore _workerSemaphore;
    private final String    _type;
    private       int       _data;
    private final Random    _random;
    private final Lock      _lock;

    public Sensor(Semaphore workerSemaphore, String type) {
        _workerSemaphore = workerSemaphore;
        _type            = type;
        _random          = new Random();
        _lock            = new ReentrantLock();
    }

    public void restart() {
        if (_lock.tryLock()) _lock.unlock();
    }

    @Override
    public void run() {
        while (true)
            try {
                _lock.lockInterruptibly();
                Thread.sleep(_random.nextInt(500));
                _data = _random.nextInt(100);
                _workerSemaphore.release();
            } catch (InterruptedException e) {
                System.out.printf("Sensor %s done%n", _type);
                break;
            }
    }

    @Override
    public String toString() {
        return String.format("%s: %d%n", _type, _data);
    }
}
