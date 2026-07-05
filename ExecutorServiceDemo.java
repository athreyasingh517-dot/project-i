import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class JobUnit implements Runnable {
    private int jobId;
    public JobUnit(int jobId) { this.jobId = jobId; }

    @Override
    public void run() {
        System.out.println("Processing Job unit #" + jobId + " inside runtime Context: " + Thread.currentThread().getName());
        try { Thread.sleep(200); } catch (InterruptedException e) {}
    }
}

public class ExecutorServiceDemo {
    public static void main(String[] args) {
        // Fixed context thread-pool structure containing 3 active workers
        ExecutorService pool = Executors.newFixedThreadPool(3);

        for (int i = 1; i <= 6; i++) {
            pool.execute(new JobUnit(i));
        }

        pool.shutdown(); // Closes interface operations once queued jobs finish processing
        System.out.println("All task groups successfully pushed to internal job queue engine.");
    }
}