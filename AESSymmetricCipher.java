import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.util.Base64;

public class AESSymmetricCipher {
    public static void main(String[] args) {
        try {
            String initialMessage = "Confidential Engineering Document Profile Parameters";
            
            // Generate Key Spec
            KeyGenerator gen = KeyGenerator.getInstance("AES");
            gen.init(128);
            SecretKey secretKey = gen.generateKey();

            // Setup Cipher state for Encryption operations
            Cipher aesEngine = Cipher.getInstance("AES");
            aesEngine.init(Cipher.ENCRYPT_MODE, secretKey);
            byte[] cipherBytes = aesEngine.doFinal(initialMessage.getBytes());
            String encodedCipherText = Base64.getEncoder().encodeToString(cipherBytes);
            System.out.println("Transformed AES Cipher Text String: " + encodedCipherText);

            // Setup Cipher state for Decryption operations
            aesEngine.init(Cipher.DECRYPT_MODE, secretKey);
            byte[] rawDecryptedBytes = aesEngine.doFinal(Base64.getDecoder().decode(encodedCipherText));
            System.out.println("Re-translated Raw Payload Data: " + new String(rawDecryptedBytes));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}