
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
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

public class Integrity_mech {

    public void Integrity_mech_program_end(String cardfileid, String username) {
        String path = username + "/" + cardfileid + ".dat";
        File cardfile = new File(path);
        try {
            /////////////////////////////////////////////////////////////////
            //first hash
            SecureRandom random = new SecureRandom();
            byte[] salt = new byte[16];
            random.nextBytes(salt);
            System.out.println("salt: " + Arrays.toString(salt));

            KeySpec spec = new PBEKeySpec(cardfile.toString().toCharArray(), salt, 65536, 128);
            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");

            byte[] hash = factory.generateSecret(spec).getEncoded();
            System.out.println("hash: " + Arrays.toString(hash));

            /////////////////////////////////////////////////////////////////
            //secomd hash
            SecureRandom random2 = new SecureRandom();
            byte[] salt2 = new byte[16];
            random2.nextBytes(salt);
            System.out.println("salt2: " + Arrays.toString(salt));

            KeySpec spec2 = new PBEKeySpec(Arrays.toString(hash).toCharArray(), salt2, 65536, 128);
            SecretKeyFactory factory2 = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");

            byte[] hash2 = factory2.generateSecret(spec2).getEncoded();
            System.out.println("hash2: " + Arrays.toString(hash2));
            //////////////////////////////////////////////////////////////////
            //encrypt with private key
            Key secret3 = null;
            //brisko to klidi
            try ( ObjectInputStream in2 = new ObjectInputStream(new FileInputStream("RSA/privateKey.dat"))) {
                System.out.println("mpika sto try");
                secret3 = (Key) in2.readObject();
                in2.close();
                System.out.println("diabasa to private key");
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Integrity_mech.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException | ClassNotFoundException ex) {
                Logger.getLogger(Integrity_mech.class.getName()).log(Level.SEVERE, null, ex);
            }

            String firstEncrypt = AsymetricCrypt.rsaEncrypt(Arrays.toString(hash2), secret3);
            ////////////////////////////////////////////////////////////////////
            //encrypt with user key
            byte[] personlakey = null;
            byte[] salt1 = null;
            //brisko to personal key
            try ( ObjectInputStream in3 = new ObjectInputStream(new FileInputStream(username + "/" + "personalKey.dat"))) {
                System.out.println("mpika sto try");
                personlakey = (byte[]) in3.readObject();
                in3.close();
                System.out.println("diabasa to private key");

                //brisko to salt
                try ( ObjectInputStream in = new ObjectInputStream(new FileInputStream(username + "/" + username + "keys.dat"))) {
                    try {
                        salt1 = (byte[]) in.readObject();
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(Integrity_mech.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    in.close();
                } catch (IOException ex) {
                    Logger.getLogger(Integrity_mech.class.getName()).log(Level.SEVERE, null, ex);
                }
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Integrity_mech.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException | ClassNotFoundException ex) {
                Logger.getLogger(Integrity_mech.class.getName()).log(Level.SEVERE, null, ex);
            }
            String Encrypted_Signature = AES256.encrypt(firstEncrypt, Arrays.toString(personlakey), Arrays.toString(salt1));
            System.out.println("Encrypted Signature: " + Encrypted_Signature);
            File file = new File(username + "/" + "integrity_mech.dat");
            boolean fvar = file.createNewFile();
            if (fvar) {
                System.out.println("File has been created successfully");
                System.out.println("encryptd integrity stored success");
                try ( ObjectOutputStream outs22 = new ObjectOutputStream(new FileOutputStream(username + "/" + "integrity_mech.dat"))) {
                    outs22.writeObject(Encrypted_Signature);
                    outs22.flush();
                    outs22.close();
                }
            } else {
                System.out.println("File already present at the specified location");
                System.out.println("somthing went wrong");
            }
        } catch (NoSuchAlgorithmException | InvalidKeySpecException ex) {
            Logger.getLogger(Integrity_mech.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(Integrity_mech.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public String integrity_mech_login(String username) {
        ///////////////////////////////////////////////////////////////////////
        //decrypt signiture with user key
        String integrity;
        //brisko to string apo tin efarmogi toy integrity mech
        try ( ObjectInputStream in2 = new ObjectInputStream(new FileInputStream(username + "/" + "integrity_mech.dat"))) {
            System.out.println("mpika sto try");
            integrity = (String) in2.readObject();
            in2.close();
            System.out.println("diabasa to integrity mech key");

            byte[] personlakey = null;
            //brisko to personal key
            try ( ObjectInputStream in3 = new ObjectInputStream(new FileInputStream(username + "/" + "personalKey.dat"))) {
                System.out.println("mpika sto try");
                personlakey = (byte[]) in3.readObject();
                in3.close();
                System.out.println("diabasa to private key");
            }
            //brisko to salt
            byte[] salt1 = null;
            try ( ObjectInputStream in = new ObjectInputStream(new FileInputStream(username + "/" + username + "keys.dat"))) {
                try {
                    salt1 = (byte[]) in.readObject();
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(Integrity_mech.class.getName()).log(Level.SEVERE, null, ex);
                }
                in.close();
            } catch (IOException ex) {
                Logger.getLogger(Integrity_mech.class.getName()).log(Level.SEVERE, null, ex);
            }

            String Dencrypted_Signature = AES256.decrypt(integrity, Arrays.toString(personlakey), Arrays.toString(salt1));
            System.out.println("Dencrypted_Signature: " + Dencrypted_Signature);

            //////////////////////////////////////////////////////////////////
            //decrypt Dencrypted_Signature with public key
            Key secret4 = null;
            //brisko to klidi
            try ( ObjectInputStream in3 = new ObjectInputStream(new FileInputStream("RSA/publicKey.dat"))) {
                System.out.println("mpika sto try");
                secret4 = (Key) in3.readObject();
                in3.close();
                System.out.println("diabasa to private key");
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Integrity_mech.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException | ClassNotFoundException ex) {
                Logger.getLogger(Integrity_mech.class.getName()).log(Level.SEVERE, null, ex);
            }

            String integrity_mech_login_after_last_hash = AsymetricCrypt.rsaDecrypt(Dencrypted_Signature.getBytes(), secret4);
            System.out.println("integrity_mech_login_after_last_hash: " + integrity_mech_login_after_last_hash);
            File file = new File(username + "/" + "decrypted_mech_until_last_hash.dat");
            boolean fvar = file.createNewFile();
            if (fvar) {
                System.out.println("File has been created successfully");
                System.out.println("integrity_mech_login_after_last_hash  stored success");
                try ( ObjectOutputStream outs23 = new ObjectOutputStream(new FileOutputStream(username + "/" + "decrypted_mech_until_last_hash.dat"))) {
                    outs23.writeObject(integrity_mech_login_after_last_hash);
                    outs23.flush();
                    outs23.close();
                }
            } else {
                System.out.println("File already present at the specified location");
                System.out.println("somthing went wrong");
            }
            return integrity_mech_login_after_last_hash;
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Integrity_mech.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(Integrity_mech.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(Integrity_mech.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
    }

}
