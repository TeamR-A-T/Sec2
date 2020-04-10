
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.swing.JOptionPane;

public class Login_mech extends Frame {

    public Login_mech(String password, byte[] salt, String username) {
        /*
        Αυτο που κάνει αυτη η κλάση είναι το εξης: Παίρνει σαν παραμετο το username , το password
        και το salt απο την κλάση login του χχρηστη που επιχειρει να συνδεθει ( θυμηθειτε οτι η
        κλαση login εχει ηdη ψαξει να δει αν υπαρχει το username που εβαλε ο χρηστης και εφοσον το βρει 
        εχει διαβασει απο το αντιστοιχο αρχειο το salt απο το registration ) Επομένως αυτο που
        κανει ειναι να hashαρει το password του χρησρτη με το ιδιο salt που ειχε γινει το registration
        και να συκρινει το hash που εφτιαξε με αυτο που υπηρχε στο φακελο με τα αρχεια τοπυ χρηστη
         */
 /*
        Δουλευει σωσατ με ενα θεμα Χρησημοποιει και τις 2 φορες το ιδιο κλειδι
         */
        try {

            //kanei hash to pass tou xristi pou kanie login
            ArrayList<String> user = new ArrayList<>();
            KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, 65536, 128);
            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            byte[] hash = factory.generateSecret(spec).getEncoded();
            System.out.println("password  hash: " + Arrays.toString(hash));
            //psaxnei gia to pass pou einia apothikeumeo sto fakelo tou xristi
            try {
                String path = username + "/";
                File file = new File(path);
                System.out.println("path: " + path);
                FilenameFilter filter = new FilenameFilter() {
                    public boolean accept(File file, String name) {
                        return name.equals(username + ".dat");
                    }
                };
                File files[] = file.listFiles(filter);
                System.out.println("file length: " + files.length);
                for (int i = 0; i < files.length; i++) {
                    System.out.println("mpika sti for");
                    System.out.println("files: " + files[i].getName());
                    //afou brike to arxeiop you xristi diabazei ola ra dedomena tou
                }
                String existingpass;
                try ( ObjectInputStream in3 = new ObjectInputStream(new FileInputStream(username + "/" + "passFile.dat"))) {
                    System.out.println("mpika mesa sto try gia na kano read to pass");
                    existingpass = (String) in3.readObject();
                    in3.close();
                    System.out.println("diabasa to pass");
                }
                System.out.println("existingpass: " + existingpass);

                /*
                try{
                ObjectInputStream in3;
                in3 = new ObjectInputStream(new FileInputStream(username + "/" + username + ".dat"));
                Object obj;
                String read;
                while((obj=in3.readObject())!=null)
                {
                    read=(String) obj;
                    System.out.println("read: "+read);
                    user.add(read);
                }
                }catch(IOException | ClassNotFoundException x)
                {
                    System.out.println("readd eror");
                }
                 */
                //System.out.println("list:" + user);
                // System.out.println("list user size: " + user.size());
                /*
                byte[] privateKey;
                try ( ObjectInputStream in4 = new ObjectInputStream(new FileInputStream("RSA/privateKey.dat"))) {
                    System.out.println("mpika mesa sto try gia na kano read to praivate key");
                    privateKey = (byte[]) in4.readObject();
                    in4.close();
                }
                System.out.println("private key: " + Arrays.toString(privateKey));
                 */
                Key privateKey;
                try ( ObjectInputStream in4 = new ObjectInputStream(new FileInputStream("RSA/privateKey.dat"))) {
                    System.out.println("mpika mesa sto try gia na kano read to private key");
                    privateKey = (Key) in4.readObject();
                    in4.close();

                }
                System.out.println("private key: " + privateKey);

                String decryptedpass = AsymetricCrypt.rsaDecrypt(Base64.getDecoder().decode(existingpass), privateKey);
                System.out.println("hash (password-hash) dectypted and is: " + decryptedpass);
                // System.out.println("password: "+password);
                //  for (int i = 0; i < user.size(); i++) {
                // System.out.println("i: " + i);
                if (decryptedpass.equals(Arrays.toString(hash))) {
                    System.out.println("mpika stin if");
                    System.out.println("user identyfied");
                    JOptionPane.showMessageDialog(frame, "Useer Identified. Login Comlete");
                    System.out.println("pass: " + Arrays.toString(hash));

                    CardFrame c = new CardFrame();
                    // break;
                } else {
                    System.out.println("kati pige lathos");
                    JOptionPane.showMessageDialog(frame, "Wrong passWord");
                }
                //}

            } catch (IOException | ClassNotFoundException a) {
                System.out.println("eror");
            } catch (Exception ex) {
                Logger.getLogger(Login_mech.class.getName()).log(Level.SEVERE, null, ex);
            }

        } catch (InvalidKeySpecException | NoSuchAlgorithmException ex) {
            Logger.getLogger(Login_mech.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

  
}
