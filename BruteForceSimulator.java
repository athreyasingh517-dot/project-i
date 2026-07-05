import java.util.concurrent.atomic.AtomicBoolean;

class BruteForceTask extends Thread {
    private String targetSecret;
    private int characterLengthRangeStart;
    private static final AtomicBoolean crackedFlag = new AtomicBoolean(false);

    public BruteForceTask(String targetSecret, int rangeStart) {
        this.targetSecret = targetSecret;
        this.characterLengthRangeStart = rangeStart;
    }

    @Override
    public void run() {
        // Simplified programmatic search space model for numeric pin simulations (e.g., 000-999 range mapping blocks)
        int maxRangeLimit = characterLengthRangeStart + 500;
        for (int guess = characterLengthRangeStart; guess < maxRangeLimit; guess++) {
            if (crackedFlag.get()) break;

            String testPattern = String.format("%03d", guess);
            if (testPattern.equals(targetSecret)) {
                System.out.println("\n[SUCCESS] Secret compromised by " + getName() + "! Sequence Key matches: " + testPattern);
                crackedFlag.set(true);
                break;
            }
        }
    }
}

public class BruteForceSimulator {
    public static void main(String[] args) {
        String secretPin = "742"; // Target code
        System.out.println("Initializing dynamic cluster tracking vectors trying to uncover: " + secretPin);

        BruteForceTask searchBlock1 = new BruteForceTask(secretPin, 0);
        BruteForceTask searchBlock2 = new BruteForceTask(secretPin, 500);

        searchBlock1.setName("Search-Cluster-Alpha");
        searchBlock2.setName("Search-Cluster-Beta");

        searchBlock1.start();
        searchBlock2.start();
    }
}