
import java.awt.List;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Card extends JFrame implements Serializable, KeyListener {
    //private static final long   serialVersionUID = -480180817175563017;

    private String cardNumber;
    private String cardId;
    private String endDate;
    private String cardUser;
    private String card_cvv;
    // private String cardfileid;

    public Card(/*String cardfileidp,*/String cardIdp, String cardNumberp, String endDatep, String a, String b) {

        cardId = cardIdp;
        cardNumber = cardNumberp;
        endDate = endDatep;
        cardUser = a;
        card_cvv = b;
        //cardfileid=cardfileidp;
    }

    public Card() {
        // CardFrame cafr = new CardFrame();

    }

    public String get_cardId() {
        return cardId;
    }

    public String get_card_cvv() {
        return card_cvv;
    }

    public String get_card_user() {
        return cardUser;
    }

    public String get_cardNumber() {
        return cardNumber;
    }

    public String get_endDate() {
        return endDate;
    }

    public void set_endDate(String a) {
        endDate = a;
    }

    public void set_card_cvv(String a) {
        card_cvv = a;
    }

    public void set_carduser(String a) {
        cardUser = a;
    }

    public void set_cardNumber(String a) {
        cardNumber = a;
    }

    public void set_cardId(String a) {
        cardId = a;
    }

    public void addcard(String cardfileid, String username, String cardnumber, Card c) {
        System.out.println("c: " + c);
        try {
            String path = username + "/" + cardfileid + ".dat";
            File file = new File(path);
            file.getParentFile().mkdirs();
            boolean fvar = file.createNewFile();
            if (fvar) {
                System.out.println("File has been created successfully");
            } else {
                System.out.println("File already present at the specified location");
            }

            ArrayList<Card> woi = new ArrayList<>();
            //SerializationUtil.serialize(c,c.cardUser+".dat"); 
            ObjectOutputStream out;
            //FileOutputStream fout;

            try {
                System.out.println("file lenhgt: " + file.length());
                if (file.length() != 0) {
                    System.out.println("mpika stin append");
                    FileOutputStream fout = new FileOutputStream(path, true);
                    out = new ObjectOutputStream(fout) {
                        protected void writeStreamHeader1() throws IOException {
                            reset();
                        }
                    };
                    woi.add(c);
                    out.writeObject(woi);
                    System.out.println("obj to be written in file: c: " + c.toString());
                    out.flush();
                    out.close();
                    fout.close();
                    System.out.println("stream closed");
                } else if (file.length() == 0) {
                    System.out.println("den mpika stin append");
                    //file.createNewFile();
                    FileOutputStream fout = new FileOutputStream(path);
                    out = new ObjectOutputStream(fout);
                    woi.add(c);
                    out.writeObject(woi);
                    System.out.println("obj to be written in file: c: " + c.toString());
                    out.flush();
                    out.close();
                    fout.close();
                    System.out.println("stream closed");
                }

            } catch (FileNotFoundException ex) {
                //  Logger.getLogger(Card.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                //Logger.getLogger(Card.class.getName()).log(Level.SEVERE, null, ex);
            }

            //SerializationUtil.serialize(c,c.cardUser+".dat"); 
            //ObjectOutputStream out;
            //FileOutputStream fout;
            /*
            try {
                System.out.println("file lenhgt: " + file.length());
                if (file.length() != 0) {
                    System.out.println("mpika stin append");
                    FileOutputStream fout = new FileOutputStream(c.get_card_user() + ".dat", true);
                    out = new ObjectOutputStream(fout) {
                        protected void writeStreamHeader1() throws IOException {
                            reset();
                        }
                    };
                    out.writeObject(c);
                    System.out.println("obj to be written in file: c: " + c.toString());
                    out.flush();
                    out.close();
                    fout.close();
                    System.out.println("stream closed");
                } else if (file.length() == 0) {
                    System.out.println("den mpika stin append");
                    //file.createNewFile();
                    FileOutputStream fout = new FileOutputStream(c.get_card_user() + ".dat");
                    out = new ObjectOutputStream(fout);
                    out.writeObject(c);
                    System.out.println("obj to be written in file: c: " + c.toString());
                    out.flush();
                    out.close();
                    fout.close();
                    System.out.println("stream closed");
                }

            } catch (FileNotFoundException ex) {
                //  Logger.getLogger(Card.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                //Logger.getLogger(Card.class.getName()).log(Level.SEVERE, null, ex);
            }
             */
 /*
            Card s;
            Vector<Card> v = new Vector<>();
            v.add(c);
            System.out.println("v: " + v);
            try {
                System.out.println("file size:"+file.length());
                if (file.length() != 0) {
                    FileOutputStream fos = new FileOutputStream(file);
                    ObjectOutputStream oos = new ObjectOutputStream(fos) {
                        protected void writeStreamHeader1() throws IOException {
                            reset();
                        }
                    };
                    oos.writeObject(v);
                    oos.close();
                    System.out.println("data write successfully");
                } else if(file.length() == 0){
                    FileOutputStream fos = new FileOutputStream(file);
                    ObjectOutputStream oos = new ObjectOutputStream(fos);
                    oos.writeObject(v);
                    oos.close();
                    System.out.println("data write successfully");
                }

            } catch (FileNotFoundException ex) {
                // ex.printStackTrace();
            } catch (IOException ex) {
                // ex.printStackTrace();
            }
             */
        } catch (IOException ex) {
            Logger.getLogger(Card.class.getName()).log(Level.SEVERE, null, ex);

        }

    }

    /*
     public void printCards(String user) {
        ArrayList<Card> print = new ArrayList<>();
       
       //try {
       //    SerializationUtil.deserialize(user);
     // } catch (IOException | ClassNotFoundException ex) {
     //        Logger.getLogger(Card.class.getName()).log(Level.SEVERE, null, ex);
     //   }
            //FileInputStream finput;
            Object s1;
     ObjectInputStream input;
        try {
            FileInputStream finput = new FileInputStream(user);
            input = new ObjectInputStream(finput);
            
            while ((s1 = input.readObject()) != null) {
                System.out.println("mpika sti while");
                if ((s1 instanceof Card)) {
                    System.out.println("s1: " + s1.toString());
                    System.out.println("mika stin if");
                    print.add((Card) s1);
                }
                else {
                    System.out.println("mpika stin else");
                    finput.close();
                    input.close();
                    break;
                }
                System.out.println("sto telos tis while");
            }
            
            finput.close();

        } catch (FileNotFoundException ex) {
           Logger.getLogger(Card.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(Card.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        System.out.println("list size: " + print.size());
        for (int i = 0; i < print.size(); i++) {
            System.out.println(print.get(i).toString());
        }

    }
     */
    public int delete(String CardNumber, String username) {
        String[] pathnames;
        try {
            String path = username;

            File file = new File(path);
            System.out.println("path:" + path);
            FilenameFilter filter = new FilenameFilter() {
                public boolean accept(File file, String name) {
                    return name.equals(CardNumber + ".dat");
                }
            };
            File files[] = file.listFiles(filter);
            System.out.println("file length" + files.length);
            for (int i = 0; i < files.length; i++) {
                System.out.println("mpika sti for");
                System.out.println("files: " + files[i].getName());
                files[i].delete();
            }
        } catch (Exception a) {
            System.out.println("eror");
        }
        /*
         pathnames = file.list(filter);
        System.out.println("pathnames length"+pathnames.length);
        for (String pathname : pathnames) {
            System.out.println("files: " + pathname);
        }
         */

        return 1;
    }

    public Card encrypteCardData(String username, Card d) {
        //βρισκω το salt
        byte[] salt1 = null;
        byte[] personalKey = null;

        try ( ObjectInputStream in = new ObjectInputStream(new FileInputStream(username + "/" + username + "keys.dat"))) {
            try {
                salt1 = (byte[]) in.readObject();
                // user.add((String) in.readObject());

            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            }
            in.close();//se periptcsi pou kati paeis tyraba auto piraxsa 
        } catch (IOException ex) {
            Logger.getLogger(Card.class.getName()).log(Level.SEVERE, null, ex);
        }
        //βρισκω το key του χρήστη 
        try ( ObjectInputStream in1 = new ObjectInputStream(new FileInputStream(username + "/" + "personalKey.dat"))) {
            try {
                personalKey = (byte[]) in1.readObject();
                // user.add((String) in.readObject());

            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            }
            in1.close();//se periptcsi pou kati paeis tyraba auto piraxsa 
        } catch (IOException ex) {
            Logger.getLogger(Card.class.getName()).log(Level.SEVERE, null, ex);
        }

        String encryptedCard_id = AES256.encrypt(d.get_cardId(), Arrays.toString(personalKey), Arrays.toString(salt1));
        String encryptedCard_number = AES256.encrypt(d.get_cardNumber(), Arrays.toString(personalKey), Arrays.toString(salt1));
        String encryptedCard_cvv = AES256.encrypt(d.get_card_cvv(), Arrays.toString(personalKey), Arrays.toString(salt1));
        String encryptedCard_user = AES256.encrypt(d.get_card_user(), Arrays.toString(personalKey), Arrays.toString(salt1));
        String encryptedCard_date = AES256.encrypt(d.get_endDate(), Arrays.toString(personalKey), Arrays.toString(salt1));
        Card encryptedcard = new Card(encryptedCard_id, encryptedCard_number, encryptedCard_cvv, encryptedCard_user, encryptedCard_date);
        System.out.println("encryptedCard: " + encryptedcard);
        return encryptedcard;
    }

    public int login_integrity(String username, String cardfileid) {
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
            Integrity_mech integ = new Integrity_mech();
            String hash3 = integ.integrity_mech_login(username);

            if (Arrays.toString(hash2).equals(hash3)) {
                System.out.println("card are not motified withput authetication");
                return 1;
                //JOptionPane.showMessageDialog(fx, "card are not motified withput authetication");
            }
            else
            {
                System.out.println("cards motified ");
                return -1;
            }
        } catch (NoSuchAlgorithmException | InvalidKeySpecException ex) {
            Logger.getLogger(Card.class.getName()).log(Level.SEVERE, null, ex);
        }

        return 0;
    }

    @Override
    public String toString() {
        return "Crad: " + "cardId: " + cardId + " cardNumber: " + cardNumber + " endDate: " + endDate + " card user: " + cardUser + " card CVV: " + card_cvv;
    }

    @Override
    public void keyTyped(KeyEvent arg0) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyPressed(KeyEvent arg0) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyReleased(KeyEvent arg0) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
