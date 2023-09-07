package industrial;

import java.util.List;
import java.util.concurrent.Semaphore;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        var workerSemaphore = new Semaphore(0, true);
        var sensorsTypes    = List.of("Temperature", "Moist", "Light");
        var sensors         = sensorsTypes.stream().map(type -> new Sensor(workerSemaphore, type)).toList();
        var worker          = new Worker(workerSemaphore, sensors);

        worker.start();
        sensors.forEach(Thread::start);

        Thread.sleep(10000);
        worker.interrupt();
        for (Sensor sensor : sensors) {
            sensor.interrupt();
            sensor.join();
        }
    }
}
