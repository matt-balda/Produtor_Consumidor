import java.util.concurrent.Semaphore;

public class ProcessadorThread extends Thread{
    private int idThread;
    private Semaphore semaphore;

    public ProcessadorThread(int id, Semaphore semaphore) {
        this.idThread = id;
        this.semaphore = semaphore;
    }
}
