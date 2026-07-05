import java.security.*;

public class DigitalSignatureEngine {
    public static void main(String[] args) {
        try {
            byte[] transactionPayload = "Verify transfer order of context entity tracking ID #4235".getBytes();

            KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
            keyGen.initialize(2048);
            KeyPair pairs = keyGen.generateKeyPair();

            // Establish signature tracking algorithm mapping
            Signature algorithmInstance = Signature.getInstance("SHA256withRSA");
            algorithmInstance.initSign(pairs.getPrivate());
            algorithmInstance.update(transactionPayload);
            byte[] signedToken = algorithmInstance.sign();
            System.out.println("Successfully generated Verification Signature block via Key.");

            // Confirm Validity state matches Source requirements
            algorithmInstance.initVerify(pairs.getPublic());
            algorithmInstance.update(transactionPayload);
            boolean matchPassStatus = algorithmInstance.verify(signedToken);
            System.out.println("Verification validation handshake confirmation: " + matchPassStatus);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}