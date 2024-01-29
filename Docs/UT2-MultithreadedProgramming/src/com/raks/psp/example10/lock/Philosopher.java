package com.raks.psp.example10.lock;

public class Philosopher extends Thread {

    private final int  _num;
    private final Fork _leftFork;
    private final Fork _rightFork;

    public Philosopher(int num, Fork rightFork, Fork leftFork) {
        _num       = num;
        _leftFork  = leftFork;
        _rightFork = rightFork;
    }

    private void randomPause() throws InterruptedException {
        Thread.sleep((long) (Math.random() * 10));
    }

    @Override
    public void run() {
        while (true)
            try {
                System.out.printf("Philosopher %d contemplating the universe, working up an appetite%n", _num);
                randomPause();
                System.out.printf("Philosopher %d hungry; going for left fork%n", _num);
                _leftFork.lock();
                System.out.printf("Philosopher %d hungry; now going for right fork%n", _num);
                if (!_rightFork.tryLock()) {
                    System.out.printf("Philosopher %d hungry; can't get right fork, giving up left%n", _num);
                    _leftFork.unlock();
                } else {
                    System.out.printf("Philosopher %d got both forks; chowing down%n", _num);
                    randomPause();
                    System.out.printf("Philosopher %d finished eating; dropping left fork%n", _num);
                    _leftFork.unlock();
                    System.out.printf("Philosopher %d finished eating; now dropping right fork%n", _num);
                    _rightFork.unlock();
                }
            } catch (InterruptedException e) {
                System.out.printf("Philosopher %d all done%n", _num);
                break;
            }
    }

}