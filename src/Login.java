
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class Login extends Frame{

    public Login(String username, String password) {
       // ArrayList<byte[]> user = new ArrayList<>();
        ArrayList<String> user = new ArrayList<>();
        byte[] a=null;
        try {
            String path = username ;
            File file = new File(path);
            boolean fvar = file.createNewFile();
            if (fvar) {
                System.out.println("File has been created successfully");
                System.out.println("file: " + file);
                JOptionPane.showMessageDialog(frame,"Wrong userName");
                file.delete();
                System.out.println("file deleted");
            } else {
                System.out.println("File already present at the specified location");
                System.out.println("file: " + file);

                try ( ObjectInputStream in = new ObjectInputStream(new FileInputStream(username + "/" + username + "keys.dat"))) {
                    try {
                        a= (byte[]) in.readObject();
                      // user.add((String) in.readObject());
                       
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    in.close();//se periptcsi pou kati paeis tyraba auto piraxsa 
                }
              
                System.out.println("list:" + user);
                System.out.println("list user size: " + user.size());
              
                // if(user.get(i).contains(Arrays.toString(hash)))
                Login_mech V = new Login_mech(password,a, username);
            }
        } catch (IOException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
