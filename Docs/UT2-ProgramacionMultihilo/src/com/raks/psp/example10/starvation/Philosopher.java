package com.raks.psp.example10.starvation;

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
        Thread.sleep(1000 + (int) (Math.random() * 4000));
    }

    public void eat() throws InterruptedException {
        System.out.printf("Philosopher %d contemplating the universe, working up an appetite%n", _num);
        randomPause();
        System.out.printf("Philosopher %d hungry; going for forks%n", _num);
        _diningPhilosophers.acquire(_leftFork, _rightFork);
        System.out.printf("Philosopher %d got forks; chowing down%n", _num);
        randomPause();
        System.out.printf("Philosopher %d finished eating; dropping forks%n", _num);
        _diningPhilosophers.release(_leftFork, _rightFork);
    }

    @Override
    public void run() {
        for (int meal = 0; meal < 3; meal++)
            try {
                eat();
                System.out.printf("Philosopher %d finished meal %d%n", _num, meal);
            } catch (InterruptedException e) {
                break;
            }
        System.out.printf("Philosopher %d all done%n", _num);
    }

}