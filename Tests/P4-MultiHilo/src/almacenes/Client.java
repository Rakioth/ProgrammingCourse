package almacenes;

public class Client extends Thread {
    private final Gate   _gate;
    private final Store  _store;
    private final String _name;
    private final int    _maxTries;

    public Client(Gate gate, Store store, String name, int maxTries) {
        _gate     = gate;
        _store    = store;
        _name     = name;
        _maxTries = maxTries;
    }

    @Override
    public void run() {
        for (int i = 0; i < _maxTries; i++)
            if (_gate.tryEnter())
                if (_store.grabProduct()) {
                    System.out.printf("Client %s grabbed a product, yay!%n", this);
                    return;
                } else
                    System.out.printf("Client %s didn't grab a product, duh!%n", this);
            else
                try {
                    Thread.sleep((long) (100 * Math.random()));
                } catch (InterruptedException e) {
                    return;
                }
        System.out.printf("%s tried %d time before giving up%n", this, _maxTries);
    }

    @Override
    public String toString() {
        return _name;
    }
}
