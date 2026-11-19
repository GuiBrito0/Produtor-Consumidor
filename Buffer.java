package AlgoritmoSO;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class Buffer {
    private static final int TAMANHO = 5;

    private final int[] dados = new int[TAMANHO];
    private final Random random = new Random();
    private int quantidade = 0;

    private final Semaphore vazias = new Semaphore(TAMANHO);
    private final Semaphore cheias = new Semaphore(0);
    private final Semaphore mutex  = new Semaphore(1);

    public void produzir(int item) throws InterruptedException {
        if (vazias.availablePermits() == 0) {
            System.out.println("Buffer cheio, produtor esperando...");
        }
        vazias.acquire();
        mutex.acquire();

        int pos;
        do {
            pos = random.nextInt(dados.length);
        } while (dados[pos] != 0);

        dados[pos] = item;
        quantidade++;
        System.out.println("Produtor colocou " + item + " na posição " + (pos + 1) + " Buffer - " + estado());
        mutex.release();
        cheias.release();
    }

    public int consumir() throws InterruptedException {
        if (cheias.availablePermits() == 0) {
            System.out.println("Buffer vazio, consumidor esperando...");
        }
        cheias.acquire();
        mutex.acquire();

        int pos;
        do {
            pos = random.nextInt(dados.length);
        } while (dados[pos] == 0);

        int item = dados[pos];
        dados[pos] = 0;
        quantidade--;
        System.out.println("Consumidor tirou " + item + " da posição " + (pos + 1) + " Buffer - " + estado());
        mutex.release();
        vazias.release();
        return item;
    }

    private String estado() {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < dados.length; i++) {
            sb.append(dados[i] == 0 ? "  " : String.format("%2d", dados[i]));
            if (i < dados.length - 1) sb.append("|");
        }
        return sb.append("] ").append("(").append(quantidade).append("/").append(TAMANHO).append(")").toString();
    }
}
