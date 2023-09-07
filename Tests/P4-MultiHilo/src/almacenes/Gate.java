package almacenes;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Gate {
    private final Lock _lock = new ReentrantLock();

    public boolean tryEnter() {
        if (_lock.tryLock())
            try {
                return true;
            } finally {
                _lock.unlock();
            }
        return false;
    }
}
