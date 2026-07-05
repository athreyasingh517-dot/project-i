class PriorityWorker extends Thread {
    public PriorityWorker(String name) { super(name); }

    @Override
    public void run() {
        System.out.println("Thread Target: " + getName() + " | Priority Weight Level: " + getPriority() + " Started.");
        for (int i = 0; i < 3; i++) {
            System.out.println(getName() + " processing batch computation cycle...");
            Thread.yield(); // Allow higher priority threads to get scheduled more frequently
        }
    }
}

public class ThreadPriorityDemo {
    public static void main(String[] args) {
        PriorityWorker low = new PriorityWorker("Low-Priority-Task");
        PriorityWorker mid = new PriorityWorker("Normal-Priority-Task");
        PriorityWorker high = new PriorityWorker("High-Priority-Task");

        low.setPriority(Thread.MIN_PRIORITY);   // Value: 1
        mid.setPriority(Thread.NORM_PRIORITY);  // Value: 5
        high.setPriority(Thread.MAX_PRIORITY);  // Value: 10

        // High priority threads suggest scheduling precedence to JVM
        low.start();
        mid.start();
        high.start();
    }
}