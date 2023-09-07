package barberos;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BarberShop {
    private final Semaphore     _chairs;
    private final Semaphore     _barbers;
    private final Queue<Client> _clientsQueue;
    private final Lock          _clientsLock = new ReentrantLock();

    public BarberShop(int chairCount, int barberCount) {
        _chairs       = new Semaphore(chairCount);
        _barbers      = new Semaphore(barberCount, true);
        _clientsQueue = new LinkedList<>();
    }

    public boolean getAChair(Client client) {
        if (_chairs.tryAcquire()) {
            _clientsLock.lock();
            _clientsQueue.add(client);
            _clientsLock.unlock();
            return true;
        }
        return false;
    }

    public void getAShave(Client client) throws InterruptedException {
        System.out.printf("Client %s waiting for a barber%n", client);
        _barbers.acquire();
    }

    public Client getAClient() {
        _clientsLock.lock();
        if (_clientsQueue.size() > 0) {
            Client client = _clientsQueue.remove();
            _chairs.release();
            _clientsLock.unlock();
            return client;
        }
        _clientsLock.unlock();
        return null;
    }

    public void doneWith(SleepingBarber barber, Client client) {
        System.out.printf("Barber %s shaved client %s%n", barber, client);
        _barbers.release();
    }
}
