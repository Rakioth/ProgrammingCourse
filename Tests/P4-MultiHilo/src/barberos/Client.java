package barberos;

public class Client extends Thread {
    private final BarberShop _barberShop;
    private final int        _clientNumber;

    public Client(BarberShop barberShop, int clientNumber) {
        _barberShop   = barberShop;
        _clientNumber = clientNumber;
    }

    @Override
    public void run() {
        if (!_barberShop.getAChair(this)) {
            System.out.printf("No seats available, client %s is leaving%n", this);
            return;
        }
        try {
            _barberShop.getAShave(this);
        } catch (InterruptedException e) {
            System.out.printf("Client %s interrupted", this);
        }
    }

    @Override
    public String toString() {
        return String.valueOf(_clientNumber);
    }
}
