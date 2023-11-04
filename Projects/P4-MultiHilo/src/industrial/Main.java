package industrial;

import java.util.List;
import java.util.concurrent.Semaphore;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        Semaphore    workerSemaphore = new Semaphore(0, true);
        List<String> sensorsTypes    = List.of("Temperature", "Moist", "Light");
        List<Sensor> sensors         = sensorsTypes.stream().map(type -> new Sensor(workerSemaphore, type)).toList();
        Worker       worker          = new Worker(workerSemaphore, sensors);

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