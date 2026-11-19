package AlgoritmoSO;

import java.util.Random;

public class Produtor extends Thread {
    private final Buffer buffer;

    public Produtor(Buffer buffer) { this.buffer = buffer; }

    @Override
    public void run() {
        Random random = new Random();
        int proximoItem = 1;
        try {
            while (!isInterrupted()) {
                sleep(random.nextInt(1000));
                buffer.produzir(proximoItem);
                proximoItem++;
            }
        } catch (InterruptedException e) {
        }
    }
}
