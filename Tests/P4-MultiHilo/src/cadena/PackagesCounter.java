package cadena;

public class PackagesCounter {
    private int _count = 0;

    public synchronized void increase() {
        _count++;
        System.out.printf("Total packages %d%n", _count);
    }
}
