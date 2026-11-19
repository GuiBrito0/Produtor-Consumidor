package AlgoritmoSO;
import java.util.*;

public class ProdutorConsumidor {
    public static void main(String[] args) throws InterruptedException {
        Buffer buffer = new Buffer();
        Scanner sc = new Scanner(System.in);

        System.out.printf("Digite quanto tempo irá rodar o algoritmo? (segundos): ");
        int tempoExecucao = sc.nextInt();

        Produtor produtor = new Produtor(buffer);
        Consumidor consumidor = new Consumidor(buffer);

        consumidor.start();
        produtor.start();

        Thread.sleep(tempoExecucao * 1000);

        produtor.interrupt();
        consumidor.interrupt();
    }
}