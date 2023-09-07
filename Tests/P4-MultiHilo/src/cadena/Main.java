package cadena;

import java.util.Arrays;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;

public class Main {
    private static final int CHAIN_SIZE = 10;

    public static void main(String[] args) throws InterruptedException {
        var chain           = new int[CHAIN_SIZE];
        var slots           = new Semaphore(CHAIN_SIZE, true);
        var packagerLocks   = new ReentrantLock[]{new ReentrantLock(), new ReentrantLock(), new ReentrantLock()};
        var packagesCounter = new PackagesCounter();

        System.out.printf("Chain's size is: %d%n", CHAIN_SIZE);
        var dropper = new Dropper(chain, slots, packagerLocks);
        var packagers = Arrays.asList(new Packager(1, chain, slots, packagerLocks[0], packagesCounter),
                                      new Packager(2, chain, slots, packagerLocks[1], packagesCounter),
                                      new Packager(3, chain, slots, packagerLocks[2], packagesCounter));

        dropper.start();
        packagers.forEach(Thread::start);

        Thread.sleep(10000);
        dropper.interrupt();
        dropper.join();
        for (Packager packager : packagers) {
            packager.interrupt();
            packager.join();
        }
    }
}
