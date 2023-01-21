import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Semaphore;

public class ProdutorConsumidorThread extends Thread {
    private int idThread;
    private Semaphore semaphore;
    private BlockingQueue<Integer> fila;
    private volatile boolean stop = false;

    public ProdutorConsumidorThread(int id, Semaphore semaphore, BlockingQueue<Integer> fila) {
        this.idThread = id;
        this.semaphore = semaphore;
        this.fila = fila;
    }

    private void processar() {
        try {
            Thread.sleep((long) (Math.random() * 10000));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private void produzir() {
        System.out.println("Thread #" + idThread + " produzindo item");
        int item = (int) (Math.random() * 100);
        try {
            fila.put(item);
            System.out.println("Thread #" + idThread + " adicionou item " + item + " à fila");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void consumir() {
        System.out.println("Thread #" + idThread + " consumindo item");
        try {
            int item = fila.take();
            System.out.println("Thread #" + idThread + " consumiu item " + item + " da fila");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void stopThread() {
        stop = true;
    }

    public void run() {
        while (!stop) {
            produzir();
            try {
                semaphore.acquire();
                consumir();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                semaphore.release();
            }
        }
    }
}
