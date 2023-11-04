package industrial;

import java.util.List;
import java.util.Random;
import java.util.concurrent.Semaphore;

public class Worker extends Thread {

    private final Semaphore    _workerSemaphore;
    private final List<Sensor> _sensors;
    private final Random       _random;

    public Worker(Semaphore workerSemaphore, List<Sensor> sensors) {
        _workerSemaphore = workerSemaphore;
        _sensors         = sensors;
        _random          = new Random();
    }

    @Override
    public void run() {
        while (true)
            try {
                _workerSemaphore.acquire(_sensors.size());
                Thread.sleep(_random.nextInt(500));
                _sensors.forEach(System.out::println);
                _sensors.forEach(Sensor::restart);
            } catch (InterruptedException e) {
                System.out.println("Worker done");
                break;
            }
    }

}