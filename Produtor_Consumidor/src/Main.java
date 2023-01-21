import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.Semaphore;

public class Main {
    public static void main(String[] args) {
        int numThreads = 5; // number of threads to create
        int filaSize = 10; // size of the blocking queue
        int semaphorePermits = filaSize / 2; // number of permits for the semaphore

        BlockingQueue<Integer> fila = new LinkedBlockingQueue<>(filaSize);
        Semaphore semaphore = new Semaphore(semaphorePermits);

        ProdutorConsumidorThread[] threads = new ProdutorConsumidorThread[numThreads];
        for (int i = 0; i < numThreads; i++) {
            ProdutorConsumidorThread thread = new ProdutorConsumidorThread(i, semaphore, fila);
            thread.start();
            threads[i] = thread;
        }

        // some code here that allows the threads to run for a certain period of time
        // ...

        //stop the thread execution
        for(int i = 0; i < numThreads; i++){
            threads[i].stopThread();
        }
    }
}