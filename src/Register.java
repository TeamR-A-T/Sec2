
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;



public class Register {

    public byte[] showSalt;

    public Register() {
    }

    public Register(String username) throws FileNotFoundException, IOException {

        //δημιουργω το directory με το ονομα 
        CardFrame c = new CardFrame();
    }

    /*
    η συνάρτηση hashPass δημιουργεί το hash του passsword οταν γίνεται η εγγραφη του χρήστη
    
     */
    public String hashPass(String username, String password) {
        System.out.println("\n hashPass \n\n");
        System.out.println("pass before hash" + password);
        try {
            SecureRandom random = new SecureRandom();
            byte[] salt = new byte[16];
            random.nextBytes(salt);
            System.out.println("salt: " + Arrays.toString(salt));
            showSalt = salt;
            addpersonsalt(username, salt);
            KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, 65536, 128);
            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");

            byte[] hash = factory.generateSecret(spec).getEncoded();
            System.out.println("hash: " + Arrays.toString(hash));
            System.out.println("\n end hashPass \n\n");
            return Arrays.toString(hash);
            // String encryptedHash=AES.encrypt(Arrays.toString(hash),"a"); 

        } catch (NoSuchAlgorithmException | InvalidKeySpecException ex) {
            Logger.getLogger(Register.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
    }

    public void addpersonfile(String username, String pass, String email, String name, String surname) {
        System.out.println("\n addpersonsfile \n\n");
        ArrayList<String> user = new ArrayList<>();
        try {
            String path = username + "/" + username + ".dat";
            File file = new File(path);
            file.getParentFile().mkdirs();
            boolean fvar = file.createNewFile();
            if (fvar) {
                System.out.println("File has been created successfully");
            } else {
                System.out.println("File already present at the specified location");
            }
            user.add(username);
            user.add(pass);
            user.add(email);
            user.add(name);
            user.add(surname);
            //user.add(Arrays.toString(showSalt));
            System.out.println("user list: " + user);
            try ( ObjectOutputStream outs = new ObjectOutputStream(new FileOutputStream(username + "/" + username + ".dat"))) {
                outs.writeObject(user.toString());
                outs.flush();
                outs.close();
            }
        } catch (IOException ex) {
            Logger.getLogger(Register.class.getName()).log(Level.SEVERE, null, ex);
        }

        System.out.println("\n end addpersonsfile \n\n");
    }

    /*
    η συνάρτηση addpersonsalt αποθηκευει το salt που δημιουργησε η εγγραφη του χρήστη
     */
    public void addpersonsalt(String username, byte[] a) {
        System.out.println("\n addpersonsalt \n\n");
        ArrayList<byte[]> user = new ArrayList<>();
        //ArrayList<String> user = new ArrayList<>();
        try {
            String path = username + "/" + username + "keys.dat";
            File file = new File(path);
            file.getParentFile().mkdirs();
            boolean fvar = file.createNewFile();
            if (fvar) {
                System.out.println("File has been created successfully");
            } else {
                System.out.println("File already present at the specified location");
            }

            user.add(a);
            System.out.println("user list: " + user);
            try ( ObjectOutputStream outs2 = new ObjectOutputStream(new FileOutputStream(username + "/" + username + "keys.dat"))) {
                outs2.writeObject(a);
                outs2.flush();
                outs2.close();
            }
        } catch (IOException ex) {
            Logger.getLogger(Register.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("\n end addpersonsalt \n\n");
    }

    /*
    θελω συναρτηση που να παίρνει το hash και να το κρυπτογραφει
     */
    public String passHashToEncrypt(String hash, String username) {
        /*
        Αυτη η συνάρτηση παίρνει το hash του password που δημιουργησε η συναρτηση hashPass 
        και το κρυπτογραφει με το δημοσιο κλειδι της εφαρμογης και επιστρεφει την κρυπτογραφημένη αυτή 
        μορφη ου pass, η οποία μετά θα μπει ως ορισμα στη συναρτηση addpersonfile ( στη κλαση Frame )
        και θα αποθηκευτει στο προσωπικο αρχειο του χρηστη
         */
        System.out.println("\n passHashToEncrypt \n\n");
        //String secret = null;
        //ObjectInputStream in2;
        // FileInputStream f1;
        //ObjectInputStream in1;
        byte[] a = null;
        Key secret = null;
        /*
        try {
          
            f1=new FileInputStream("RSA/publicKey.dat");
            in2 = new ObjectInputStream(f1);
            secret = (String) in2.readObject();
            in2.close();
            System.out.println("mika sto proto try");

        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(Register.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("error1");
        }
         */
        try ( ObjectInputStream in2 = new ObjectInputStream(new FileInputStream("RSA/publicKey.dat"))) {
            System.out.println("mpika sto try");
            secret = (Key) in2.readObject();
            //System.out.println("ins22: "+in2);
            in2.close();
        } catch (FileNotFoundException ex) {
            System.out.println("rerror1");
            Logger.getLogger(Register.class.getName()).log(Level.SEVERE, null, ex);

        } catch (IOException ex) {
            System.out.println("error2");
            Logger.getLogger(Register.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Register.class.getName()).log(Level.SEVERE, null, ex);
        }

        System.out.println("secret: " + secret);
        /*
        try {

            in1 = new ObjectInputStream(new FileInputStream(username + "/" + username + "keys.dat"));
            a = (byte[]) in1.readObject();
            in1.close();

        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(Register.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("reoor2");
        }
         */
        try ( ObjectInputStream in1 = new ObjectInputStream(new FileInputStream(username + "/" + username + "keys.dat"))) {
            System.out.println("mpika sto deytero  try");
            a = (byte[]) in1.readObject();
            in1.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Register.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(Register.class.getName()).log(Level.SEVERE, null, ex);
        }

        System.out.println("hash: " + hash);
        // String resultOfHashEncrypt = AES256.encrypt(hash, Arrays.toString(secret), Arrays.toString(a));
        String resultOfHashEncrypt = null;
        try {
            resultOfHashEncrypt = AsymetricCrypt.rsaEncrypt(hash, secret);
        } catch (Exception ex) {
            Logger.getLogger(Register.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("encrypted hash:" + resultOfHashEncrypt);
        System.out.println("\n end  passHashToEncrypt \n\n");
        return resultOfHashEncrypt;
    }

    public void passWordFle(String pass, String username) {
        try {
            String path = username + "/" + "passFile.dat";
            File file = new File(path);
            file.getParentFile().mkdirs();
            boolean fvar = file.createNewFile();
            if (fvar) {
                System.out.println("File has been created successfully");
            } else {
                System.out.println("File already present at the specified location");
            }

            try ( ObjectOutputStream outs22 = new ObjectOutputStream(new FileOutputStream(path))) {
                outs22.writeObject(pass);
                outs22.flush();
                outs22.close();
            }
        } catch (IOException ex) {
            Logger.getLogger(Register.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("\n end addpersonsalt \n\n");
    }

     
    public void setPersonSymetricKey(String username) {
        System.out.println("\n start setPersonSymetricKey \n");
        try {
            KeyGenerator keyGen = KeyGenerator.getInstance("AES");
            keyGen.init(256);
            SecretKey secretKey = keyGen.generateKey();
            byte[] encoded = secretKey.getEncoded();
            System.out.println("key: "+Arrays.toString(encoded));
            System.out.println("secretKey: "+secretKey);
        
            String path = username + "/" + "personalKey.dat";
            File file = new File(path);
            file.getParentFile().mkdirs();
            boolean fvar = file.createNewFile();
            if (fvar) {
                System.out.println("File has been created successfully");
            } else {
                System.out.println("File already present at the specified location");
            }
            try ( ObjectOutputStream outs22 = new ObjectOutputStream(new FileOutputStream(path))) {
                outs22.writeObject(encoded);
                outs22.flush();
                outs22.close();
            }
        } catch (IOException | NoSuchAlgorithmException ex) {
            Logger.getLogger(Register.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("\n end setPersonSymetricKey \n\n");
    }
}
