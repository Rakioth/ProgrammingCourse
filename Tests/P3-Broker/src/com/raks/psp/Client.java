package com.raks.psp;

public class Client extends Thread {
    private static final int MIN_BUY  = 1;
    private static final int MAX_BUY  = 5;
    private static final int MIN_WAIT = 1000;
    private static final int MAX_WAIT = 2000;

    private final String _name;
    private final Broker _broker;
    private       int    _ownedShares;

    public Client(String name, Broker broker) {
        _name        = name;
        _broker      = broker;
        _ownedShares = 0;
    }

    @Override
    public void run() {
        while (true)
            try {
                Thread.sleep((long) ((Math.random() * (MAX_WAIT - MIN_WAIT + 1)) + MIN_WAIT));
                int sharesToBuy = _broker.getShares() <= 5 ? _broker.getShares() : (int) ((Math.random() * (MAX_BUY - MIN_BUY + 1)) + MIN_BUY);
                System.out.printf("Client %s about to buy %d shares%n", _name, sharesToBuy);
                if (_broker.buy(sharesToBuy)) {
                    _ownedShares += sharesToBuy;
                    System.out.printf("Client %s bought %d shares%n", _name, sharesToBuy);
                } else System.out.printf("Client %s couldn't buy %d shares%n", _name, sharesToBuy);
            } catch (InterruptedException e) {
                System.out.printf("Client %s bought %d shares in total%n", _name, _ownedShares);
                break;
            }
    }
}
