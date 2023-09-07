package banco;

public class Main {
    private static final int NUM_40_CLIENTS = 40;
    private static final int NUM_20_CLIENTS = 20;
    private static final int NUM_60_CLIENTS = 60;

    public static void main(String[] args) throws InterruptedException {
        var account            = new Account(100);
        var withdraw100Clients = new Thread[NUM_40_CLIENTS];
        var withdraw50Clients  = new Thread[NUM_20_CLIENTS];
        var withdraw20Clients  = new Thread[NUM_60_CLIENTS];
        var deposit100Clients  = new Thread[NUM_40_CLIENTS];
        var deposit50Clients   = new Thread[NUM_20_CLIENTS];
        var deposit20Clients   = new Thread[NUM_60_CLIENTS];

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
