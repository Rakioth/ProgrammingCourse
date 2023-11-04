package com.raks.psp;

import java.util.List;

public class Main {

    private static final int MAX_SHARES = 20;

    public static void main(String[] args) throws InterruptedException {
        Broker       broker  = new Broker(MAX_SHARES);
        List<String> names   = List.of("María", "Vanessa", "Luis", "Alberto");
        List<Client> clients = names.stream().map(name -> new Client(name, broker)).toList();

        clients.forEach(Thread::start);
        broker.waitUntilNoSharesAvailable();
        for (Client client : clients) {
            client.interrupt();
            client.join();
        }
    }

}