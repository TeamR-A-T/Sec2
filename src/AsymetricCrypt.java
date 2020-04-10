
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.Cipher;

public class AsymetricCrypt {
    private PrivateKey privateKey;
    private PublicKey publicKey;
    static int RSA_KEY_LENGTH = 4096;
    static String ALGORITHM_NAME = "RSA";
    static String PADDING_SCHEME = "OAEPWITHSHA-512ANDMGF1PADDING";
    static String MODE_OF_OPERATION = "ECB"; // This essentially means none behind the scene

    public void setKeyPair() {
        try {
            // Generate Key Pairs
            KeyPairGenerator rsaKeyGen = KeyPairGenerator.getInstance(ALGORITHM_NAME);
            rsaKeyGen.initialize(RSA_KEY_LENGTH);
            KeyPair rsaKeyPair = rsaKeyGen.generateKeyPair();
            this.privateKey = rsaKeyPair.getPrivate();
            this.publicKey = rsaKeyPair.getPublic();
                
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(AsymetricCrypt.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void writeToFile(String path, Key key) throws IOException {
        File f = new File(path);
        f.getParentFile().mkdirs();
        try ( ObjectOutputStream outs2 = new ObjectOutputStream(new FileOutputStream(f))) {
            outs2.writeObject(key);
            outs2.flush();
            outs2.close();
        }
        /*
        FileOutputStream fos = new FileOutputStream(f);
        fos.write(key);
        fos.flush();
        fos.close();
         */
    }

    public static String rsaEncrypt(String message, Key publicKey) throws Exception {
        Cipher c = Cipher.getInstance(ALGORITHM_NAME + "/" + MODE_OF_OPERATION + "/" + PADDING_SCHEME);
        c.init(Cipher.ENCRYPT_MODE, publicKey);
        byte[] cipherTextArray = c.doFinal(message.getBytes());

        return Base64.getEncoder().encodeToString(cipherTextArray);

    }

    public static String rsaDecrypt(byte[] encryptedMessage, Key privateKey) throws Exception {
        Cipher c = Cipher.getInstance(ALGORITHM_NAME + "/" + MODE_OF_OPERATION + "/" + PADDING_SCHEME);
        c.init(Cipher.DECRYPT_MODE, privateKey);
        byte[] plainText = c.doFinal(encryptedMessage);

        return new String(plainText);

    }
    public PrivateKey getPrivateKey() {
        return privateKey;
    }

    public PublicKey getPublicKey() {
        return publicKey;
    }
}
