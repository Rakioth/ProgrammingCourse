package com.raks.psp.example10.deadlock;

public class Philosopher extends Thread {
    private final DiningPhilosophers _diningPhilosophers;
    private final int                _num;
    private final Fork               _leftFork;
    private final Fork               _rightFork;

    public Philosopher(DiningPhilosophers monitor, int num, Fork rightFork, Fork leftFork) {
        _diningPhilosophers = monitor;
        _num                = num;
        _leftFork           = leftFork;
        _rightFork          = rightFork;
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
                _diningPhilosophers.acquire(_leftFork);
                System.out.printf("Philosopher %d hungry; now going for right fork%n", _num);
                _diningPhilosophers.acquire(_rightFork);
                System.out.printf("Philosopher %d got both forks; chowing down%n", _num);
                randomPause();
                System.out.printf("Philosopher %d finished eating; dropping left fork%n", _num);
                _diningPhilosophers.release(_leftFork);
                System.out.printf("Philosopher %d finished eating; now dropping right fork%n", _num);
                _diningPhilosophers.release(_rightFork);
            } catch (InterruptedException e) {
                System.out.printf("Philosopher %d all done%n", _num);
                break;
            }
    }
}
