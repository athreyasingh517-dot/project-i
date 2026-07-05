import java.security.MessageDigest;

public class PasswordHasher {
    public static void main(String[] args) {
        try {
            String targetPasswordString = "SecureUserPass773";

            // Initialize MessageDigest with SHA-256
            MessageDigest digestEngine = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = digestEngine.digest(targetPasswordString.getBytes());

            // Convert byte array into human-readable Hex String format
            StringBuilder hexStringOutput = new StringBuilder();
            for (byte b : hashBytes) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexStringOutput.append('0');
                hexStringOutput.append(hex);
            }

            System.out.println("Original String Input: " + targetPasswordString);
            System.out.println("Calculated SHA-256 One-Way Hash Metric: " + hexStringOutput.toString());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}