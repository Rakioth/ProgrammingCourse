package barberos;

import java.util.concurrent.ThreadLocalRandom;

public class SleepingBarber extends Thread {
    private final BarberShop        _barberShop;
    private final int               _barberNumber;
    private final ThreadLocalRandom _threadLocalRandom;

    public SleepingBarber(BarberShop barberShop, int barberNumber) {
        _barberShop        = barberShop;
        _barberNumber      = barberNumber;
        _threadLocalRandom = ThreadLocalRandom.current();
    }

    public void randomSleep() throws InterruptedException {
        Thread.sleep(_threadLocalRandom.nextInt(1000, 5000));
    }

    @Override
    public void run() {
        while (true)
            try {
                Client client;
                while ((client = _barberShop.getAClient()) == null) randomSleep();
                System.out.printf("Barber %s shaving client %s%n", this, client);
                randomSleep();
                _barberShop.doneWith(this, client);
            } catch (InterruptedException e) {
                break;
            }
    }

    @Override
    public String toString() {
        return String.valueOf(_barberNumber);
    }
}
