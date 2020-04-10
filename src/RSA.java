
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.security.*;

public class RSA {

    private PrivateKey privateKey;
    private PublicKey publicKey;

    public RSA() throws NoSuchAlgorithmException {
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
        keyGen.initialize(1024);
        KeyPair pair = keyGen.generateKeyPair();
        this.privateKey = pair.getPrivate();
        this.publicKey = pair.getPublic();
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

    public PrivateKey getPrivateKey() {
        return privateKey;
    }

    public PublicKey getPublicKey() {
        return publicKey;
    }

    public String getkey() {
        return publicKey.toString();
    }

}
