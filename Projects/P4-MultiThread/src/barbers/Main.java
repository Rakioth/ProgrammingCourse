package barbers;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {

    private static final int BARBERS     = 2;
    private static final int CHAIRS      = BARBERS + 1;
    private static final int MAX_CLIENTS = BARBERS * 10;
    private static final int MAX_WAIT    = 3;

    public static void main(String[] args) throws InterruptedException {
        BarberShop           barberShop      = new BarberShop(CHAIRS, BARBERS);
        List<Client>         clients         = new ArrayList<>(MAX_CLIENTS);
        List<SleepingBarber> sleepingBarbers = new ArrayList<>(BARBERS);
        Random               random          = new Random();

        for (int i = 0; i < BARBERS; i++) {
            sleepingBarbers.add(new SleepingBarber(barberShop, i));
            sleepingBarbers.get(i).start();
        }
        for (int i = 0; i < MAX_CLIENTS; i++) {
            clients.add(new Client(barberShop, i));
            clients.get(i).start();
            Thread.sleep(random.nextInt(MAX_WAIT) * 1000);
        }

        for (Client client : clients)
            client.join();
        System.out.println("No more clients, done");

        for (SleepingBarber barber : sleepingBarbers) {
            barber.interrupt();
            barber.join();
        }
        System.out.println("Barbers done");
    }

}