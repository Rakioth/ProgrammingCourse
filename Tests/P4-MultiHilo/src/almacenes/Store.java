package almacenes;

public class Store {
    private int _numProducts;

    public Store(int numProducts) {
        _numProducts = numProducts;
    }

    public synchronized boolean grabProduct() {
        if (_numProducts > 0) {
            _numProducts--;
            return true;
        }
        return false;
    }
}
