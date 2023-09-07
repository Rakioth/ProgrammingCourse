package com.raks.psp;

import java.util.List;

public class Main {
    private static final int MAX_SHARES = 20;

    public static void main(String[] args) throws InterruptedException {
        var broker  = new Broker(MAX_SHARES);
        var names   = List.of("María", "Vanessa", "Luis", "Alberto");
        var clients = names.stream().map(name -> new Client(name, broker)).toList();

        clients.forEach(Thread::start);
        broker.waitUntilNoSharesAvailable();
        for (Client client : clients) {
            client.interrupt();
            client.join();
        }
    }
}
