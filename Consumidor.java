package AlgoritmoSO;

import java.util.Random;

public class Consumidor extends Thread {
    private final Buffer buffer;

    public Consumidor(Buffer buffer) { this.buffer = buffer; }

    @Override
    public void run() {
        Random random = new Random();
        try {
            while (!isInterrupted()) {
                buffer.consumir();
                sleep(random.nextInt(1200));
            }
        } catch (InterruptedException e) {
        }
    }
}
