class SharedBuffer {
    private int content;
    private boolean available = false;

    public synchronized int consume() {
        while (!available) {
            try { wait(); } catch (InterruptedException e) {}
        }
        available = false;
        System.out.println("Consumed Data Value: " + content);
        notifyAll();
        return content;
    }

    public synchronized void produce(int value) {
        while (available) {
            try { wait(); } catch (InterruptedException e) {}
        }
        this.content = value;
        available = true;
        System.out.println("Produced Data Value: " + content);
        notifyAll();
    }
}

public class ProducerConsumerDemo {
    public static void main(String[] args) {
        SharedBuffer buffer = new SharedBuffer();

        Thread producer = new Thread(() -> {
            for (int i = 1; i <= 5; i++) buffer.produce(i);
        });

        Thread consumer = new Thread(() -> {
            for (int i = 1; i <= 5; i++) buffer.consume();
        });

        producer.start();
        consumer.start();
    }
}