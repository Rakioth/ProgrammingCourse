package warehouses;

import java.util.ArrayList;
import java.util.List;

public class Main {

    private static final int NUM_CLIENTS  = 300;
    private static final int NUM_PRODUCTS = 100;
    private static final int MAX_TRIES    = 10;

    public static void main(String[] args) throws InterruptedException {
        List<Client> clients = new ArrayList<>(NUM_CLIENTS);
        Store        store   = new Store(NUM_PRODUCTS);
        Gate         gate    = new Gate();

        for (int i = 0; i < NUM_CLIENTS; i++) {
            clients.add(new Client(gate, store, String.format("%03d", i), MAX_TRIES));
            clients.get(i).start();
        }
        for (Client client : clients)
            client.join();
    }

}