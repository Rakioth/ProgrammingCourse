package com.raks.psp;

public class Broker {
    private static final int MIN_SHARES = 10;

    private int _shares;

    public Broker(int shares) {
        if (shares <= MIN_SHARES) shares = MIN_SHARES;
        _shares = shares;
    }

    public int getShares() {
        return _shares;
    }

    public synchronized boolean buy(int buyingShares) {
        System.out.printf("Broker: there's %d available shares%n", _shares);
        if (buyingShares > _shares) return false;
        _shares -= buyingShares;
        System.out.printf("Broker: now there's %d available shares%n", _shares);
        notifyAll();
        return true;
    }

    public synchronized void waitUntilNoSharesAvailable() throws InterruptedException {
        while (_shares > 0) wait();
    }
}
