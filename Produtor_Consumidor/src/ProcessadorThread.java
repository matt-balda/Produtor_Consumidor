import java.util.concurrent.Semaphore;

public class ProcessadorThread extends Thread{
    private int idThread;
    private Semaphore semaphore;

    public ProcessadorThread(int id, Semaphore semaphore) {
        this.idThread = id;
        this.semaphore = semaphore;
    }

    private void processar() {
        try {
            System.out.println("Thread #"+idThread+" processando");
            Thread.sleep((long) (Math.random() * 10000));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    private void entrarRegiaoNaoCritica() {
        System.out.println("Thread #"+idThread+" em região não crítica");
        processar();
    }

    private void entrarRegioCritica() {
        System.out.println("Thread #"+idThread+" entrando em região crítica");
        processar();
        System.out.println("Thread #"+idThread+" sainda da região crítica");
    }

    public void run() {
        entrarRegiaoNaoCritica();
        try {
            semaphore.acquire();
            entrarRegioCritica();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            semaphore.release();
        }
    }
}
