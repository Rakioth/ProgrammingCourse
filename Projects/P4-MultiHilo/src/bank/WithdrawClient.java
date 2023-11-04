package bank;

public class WithdrawClient extends Thread {

    private final Account _account;
    private final int     _amount;

    public WithdrawClient(Account account, int amount) {
        _account = account;
        _amount  = amount;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++)
             _account.withdraw(_amount);
    }

}