
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class Frame extends JFrame {

    /*
    Δημιουργω 2 buttons 1 για καθε λειτουργία και άλλα 2 για να ολοκληρωνουν την εκάστοτε λειτουργεια + exitButton
    login->login_1 login
    register->register_1 register
    tl->JLabel
    tf->JTextField
    login: tl1 & tl2 / tf1 & tf2
    register: tl3 & tl4 / tf3 & tf4
     */
     public JFrame frame = new JFrame("welcome");
    public Frame() {
        //frame
       
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setBounds(700, 150, 400, 400);
        JLabel ask = new JLabel("login or register?");
        ask.setBounds(140, 0, 150, 50);
        frame.add(ask);
        //login 
        //login frame
        JFrame Lframe = new JFrame("Login Form");
        //button to login
        JButton login = new JButton("login Form");
        login.setBounds(0, 70, 200, 200);
        login.setVisible(true);
        JButton login_1 = new JButton("login");
        //login fields
        JLabel tl1 = new JLabel(" UserName: ");
        JTextField tf1 = new JTextField();
        JLabel tl2 = new JLabel(" PassWord ");
        JPasswordField tf2 = new JPasswordField();

        //register
        //register frame
        JFrame Rframe = new JFrame("Register Form");
        //button to register
        JButton register = new JButton(" Rgistration form");
        JButton register_1 = new JButton(" Rgistration");
        register.setBounds(200, 70, 200, 200);
        register.setVisible(true);
        //register fields
        JLabel tl3 = new JLabel(" UserName: ");
        JTextField tf3 = new JTextField();
        JLabel tl4 = new JLabel(" PassWord ");
        JPasswordField tf4 = new JPasswordField();
        JLabel tl5 = new JLabel(" email ");
        JTextField tf5 = new JTextField();
        JLabel tl6 = new JLabel(" Name ");
        JTextField tf6 = new JTextField();
        JLabel tl7 = new JLabel(" Surname ");
        JTextField tf7 = new JTextField();

        frame.add(login);
        frame.add(register);
        frame.setVisible(true);

        //button to exit
        JButton exit = new JButton(" exit from Card Configuration");

        login.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //frame

                Lframe.setDefaultCloseOperation(EXIT_ON_CLOSE);
                Lframe.setLayout(null);
                Lframe.setBounds(700, 150, 400, 400);
                Lframe.setVisible(true);

                //username
                tl1.setBounds(20, 0, 150, 30);
                tf1.setBounds(150, 0, 150, 30);
                Lframe.add(tl1);
                Lframe.add(tf1);

                //password
                tl2.setBounds(20, 50, 150, 30);
                tf2.setBounds(150, 50, 150, 30);
                Lframe.add(tl2);
                Lframe.add(tf2);

                login_1.setVisible(true);
                login_1.setBounds(150, 100, 150, 30);
                Lframe.add(login_1);

            }
        }
        );
        login_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Login l= new Login(tf1.getText(),tf2.getText());
                
                tf1.setText(null);
                tf2.setText(null);
                Lframe.setVisible(false);
                //Card c=new Card(); 
                
                
            }
        }
        );
        register.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //frame

                Rframe.setDefaultCloseOperation(EXIT_ON_CLOSE);
                Rframe.setLayout(null);
                Rframe.setBounds(700, 150, 400, 400);
                Rframe.setVisible(true);

                //username
                tl3.setBounds(20, 0, 150, 30);
                tf3.setBounds(150, 0, 150, 30);
                Rframe.add(tl3);
                Rframe.add(tf3);

                //password
                tl4.setBounds(20, 50, 150, 30);
                tf4.setBounds(150, 50, 150, 30);
                Rframe.add(tl4);
                Rframe.add(tf4);

                //email
                tl5.setBounds(20, 100, 150, 30);
                tf5.setBounds(150, 100, 150, 30);
                Rframe.add(tl5);
                Rframe.add(tf5);

                //name
                tl6.setBounds(20, 150, 150, 30);
                tf6.setBounds(150, 150, 150, 30);
                Rframe.add(tl6);
                Rframe.add(tf6);

                //surname
                tl7.setBounds(20, 200, 150, 30);
                tf7.setBounds(150, 200, 150, 30);
                Rframe.add(tl7);
                Rframe.add(tf7);

                register_1.setVisible(true);
                register_1.setBounds(150, 250, 150, 30);
                Rframe.add(register_1);

            }
        }
        );
        register_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                try {
                    Register r = new Register(tf3.getText());
                    String hashedPass=r.hashPass(tf3.getText(),tf4.getText());
                    String encryptedpass=r.passHashToEncrypt(hashedPass, tf3.getText());
                    r.passWordFle(encryptedpass, tf3.getText());
                    r.addpersonfile(tf3.getText(),encryptedpass,tf5.getText(),tf6.getText(),tf7.getText());
                    r.setPersonSymetricKey(tf3.getText());
                } catch (IOException ex) {
                    Logger.getLogger(Frame.class.getName()).log(Level.SEVERE, null, ex);
                }
               
                
                tf3.setText(null);
                tf4.setText(null);
                tf5.setText(null);
                tf6.setText(null);
                tf7.setText(null);
                Rframe.setVisible(false);
               // Card c=new Card();
            }
        }
        );
        exit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                System.exit(0);

            }
        }
        );
        frame.add(exit);
        exit.setVisible(true);
        exit.setBounds(182, 310, 200, 50);
    }
}
