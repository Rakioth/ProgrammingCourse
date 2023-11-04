package bank;

public class Account {

    private final int _initialBalance;
    private       int _balance;

    public Account(int initialBalance) {
        _initialBalance = initialBalance;
        _balance        = initialBalance;
    }

    public synchronized void deposit(int amount) {
        _balance += amount;
    }

    public synchronized void withdraw(int amount) {
        _balance -= amount;
    }

    public synchronized boolean simulationSucceeded() {
        return _balance == _initialBalance;
    }

    public synchronized int getBalance() {
        return _balance;
    }

}