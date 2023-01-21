import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Semaphore;

public class Main {
    public static void main(String[] args) {
        int numThreads = 100; // numero de thread para criar
        int filaSize = 100; // tamanho da fila de bloqueio
        int semaphorePermits = filaSize / 2; // numero de licenças para o semáforo

        Semaphore semaphore = new Semaphore(semaphorePermits);
        Semaphore semaphore2 = new Semaphore(semaphorePermits); // segunda Thread, para servi como DeadLocK
        BlockingQueue<Integer> fila = new ArrayBlockingQueue<Integer>(filaSize);

        ProdutorConsumidorThread[] threads = new ProdutorConsumidorThread[numThreads];
        for (int i = 0; i < numThreads; i++) {
            threads[i] = new ProdutorConsumidorThread(i, semaphore,semaphore2, fila);
            threads[i].start();
        }

        for(int i = 0; i < numThreads; i++){
            threads[i].stopThread();
        }
    }
}