
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main implements Serializable
{
    
    public static void main(String[] args) {
       
        //RSA algorythm for first app opening
        try {
            File file = new File("first_open.dat");
            boolean fvar = file.createNewFile();
            if (fvar) {
                AsymetricCrypt as= new AsymetricCrypt();
                as.setKeyPair();
                System.out.println("File has been created successfully");
               // RSA keyPairGenerator = new RSA();
                as.writeToFile("RSA/publicKey.dat", as.getPublicKey());
                as.writeToFile("RSA/privateKey.dat", as.getPrivateKey());
                System.out.println(" app's Public key "+as.getPublicKey());
                
                System.out.println(" app's Private key "+as.getPrivateKey());
            } else {
                System.out.println("File already present at the specified location");
                System.out.println("continue");
            }
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        
  
        /*
            AES256 algorythm
            final String secretKey = "ssshhhhhhhhhhh!!!!";
            Card c1 = new Card("2", "1234567890123456", "02-02-2022", "manos", "123");
            String salt = "ssshhhhhhhhhhh!!!!";
            String encryptedString = AES256.encrypt(c1.toString(), secretKey,salt);
            String decryptedString = AES256.decrypt(encryptedString, secretKey,salt);
            System.out.println("c1 before encrypt: " + c1);
            
            System.out.println(encryptedString);
            System.out.println(decryptedString);
         */

        // String fileName="manos.dat";
       Frame fr = new Frame();
      // Register r= new Register();
       //
        //Card c=new Card();

        /*
            RSA algorythm for first app opening
            try {
            RSA keyPairGenerator = new RSA();
            keyPairGenerator.writeToFile("RSA/publicKey", keyPairGenerator.getPublicKey().getEncoded());
            keyPairGenerator.writeToFile("RSA/privateKey", keyPairGenerator.getPrivateKey().getEncoded());
            System.out.println(Base64.getEncoder().encodeToString(keyPairGenerator.getPublicKey().getEncoded()));
            System.out.println(Base64.getEncoder().encodeToString(keyPairGenerator.getPrivateKey().getEncoded()));
            } catch (IOException | NoSuchAlgorithmException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
         */
        
       /*
    INTEGRITY MEC 
      byte[] a={68, -95, 50, 50, 97, 8, 114, -83, 72, 114, -51, 61, -8, 29, 55, 56};
     Integrity_mech V = new Integrity_mech("p",a,"p");
      */
     
    }

}
