import java.io.*;

class ScanTask extends Thread {
    private File fileToScan;
    private String matchFlag;

    public ScanTask(File fileToScan, String matchFlag) {
        this.fileToScan = fileToScan;
        this.matchFlag = matchFlag;
    }

    @Override
    public void run() {
        try (BufferedReader br = new BufferedReader(new FileReader(fileToScan))) {
            String line;
            int lineNum = 1;
            while ((line = br.readLine()) != null) {
                if (line.contains(matchFlag)) {
                    System.out.println("[ALERT] Match found in " + fileToScan.getName() + " on Line " + lineNum);
                }
                lineNum++;
            }
        } catch (IOException e) {
            System.err.println("Unreadable file path parameters matching target rules: " + fileToScan.getName());
        }
    }
}

public class ParallelFileScanner {
    public static void main(String[] args) {
        File dir = new File("./scan_target_folder"); // Match path to local testing folder
        String searchTarget = "SUSPICIOUS";

        // Create directory fallback if missing
        if (!dir.exists()) dir.mkdir();

        File[] list = dir.listFiles();
        if (list != null) {
            for (File f : list) {
                if (f.isFile() && f.getName().endsWith(".txt")) {
                    new ScanTask(f, searchTarget).start();
                }
            }
        } else {
            System.out.println("Directory is empty.");
        }
    }
}