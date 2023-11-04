package bank;

public class Main {

    private static final int NUM_40_CLIENTS = 40;
    private static final int NUM_20_CLIENTS = 20;
    private static final int NUM_60_CLIENTS = 60;

    public static void main(String[] args) throws InterruptedException {
        Account  account            = new Account(100);
        Thread[] withdraw100Clients = new Thread[NUM_40_CLIENTS];
        Thread[] withdraw50Clients  = new Thread[NUM_20_CLIENTS];
        Thread[] withdraw20Clients  = new Thread[NUM_60_CLIENTS];
        Thread[] deposit100Clients  = new Thread[NUM_40_CLIENTS];
        Thread[] deposit50Clients   = new Thread[NUM_20_CLIENTS];
        Thread[] deposit20Clients   = new Thread[NUM_60_CLIENTS];

        for (int i = 0; i < NUM_40_CLIENTS; i++) {
            withdraw100Clients[i] = new WithdrawClient(account, 100);
            deposit100Clients[i]  = new DepositClient(account, 100);
            withdraw100Clients[i].start();
            deposit100Clients[i].start();
        }
        for (int i = 0; i < NUM_20_CLIENTS; i++) {
            withdraw50Clients[i] = new WithdrawClient(account, 50);
            deposit50Clients[i]  = new DepositClient(account, 50);
            withdraw50Clients[i].start();
            deposit50Clients[i].start();
        }
        for (int i = 0; i < NUM_60_CLIENTS; i++) {
            withdraw20Clients[i] = new WithdrawClient(account, 20);
            deposit20Clients[i]  = new DepositClient(account, 20);
            withdraw20Clients[i].start();
            deposit20Clients[i].start();
        }

        for (int i = 0; i < NUM_40_CLIENTS; i++) {
            withdraw100Clients[i].join();
            deposit100Clients[i].join();
        }
        for (int i = 0; i < NUM_20_CLIENTS; i++) {
            withdraw50Clients[i].join();
            deposit50Clients[i].join();
        }
        for (int i = 0; i < NUM_60_CLIENTS; i++) {
            withdraw20Clients[i].join();
            deposit20Clients[i].join();
        }

        if (account.simulationSucceeded())
            System.out.println("Simulation Succeded!");
        else
            System.out.printf("Simulation Failed! Final balance is %d, check your synchronization%n", account.getBalance());
    }

}