
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class CardFrame extends Card//extends JFrame 
{

    /*
    Δημιουργω 4 buttons 1 για καθε λειτουργία και άλλα 4 για να ολοκληρωνουν την εκάστοτε λειτουργεια + exitButton
    but1->but1_1 add card
    but2->but2_1 Modify card
    but3->but3_1 delete card
    but4->but4_1 print cards
    tl->JLabel
    tf->JTextField
    Add crad: tl1 -> tl5 / tf1 -> tf5
    Modify Crad tl8 -> tl12 /tf8 -> tf12
    Delete Card  tl6 and tl7 / tf6 and tf7
    print card
     */
    public CardFrame() {

        // main frame
        JFrame f = new JFrame("Digital Wallet");
        f.setDefaultCloseOperation(EXIT_ON_CLOSE);
        f.setLayout(null);
        f.setBounds(700, 150, 800, 800);
        f.setVisible(true);

        JLabel help5 = new JLabel("Tip0: Card file id: Enter the name of the file tha card will be stored");
        help5.setBounds(0, 600, 550, 30);
        f.add(help5);
        JLabel help4 = new JLabel("Tip1: Card Id: Visa ,Master Card ,diners");
        help4.setBounds(0, 630, 250, 30);
        f.add(help4);
        JLabel help1 = new JLabel("Tip2: Card Number : 16 Numbers");
        help1.setBounds(0, 660, 200, 30);
        f.add(help1);
        JLabel help2 = new JLabel("Tip3: Card CVV: 3 Numbers");
        help2.setBounds(0, 690, 200, 30);
        f.add(help2);
        JLabel help3 = new JLabel("Tip4: Date fromat: MM-dd-yyyy");
        help3.setBounds(0, 720, 200, 30);
        f.add(help3);
        JLabel welcome = new JLabel("WELCOME TO YOUR DIGITAL WALLET. YOU CAN MANAGE YOUR CARD FROM HERE");
        welcome.setBounds(150, 200, 500, 200);
        f.add(welcome);

        //exitbutton
        JButton exit = new JButton("Exit from my Digial Wallet");

        //Add card 
        //add card frame
        JFrame f1 = new JFrame("insert Card");
        //Card id 
        JLabel tl1 = new JLabel("    Card id: ");
        JTextField tf1 = new JTextField();
        //Card Number
        JLabel tl2 = new JLabel("Card Number: ");
        JTextField tf2 = new JTextField();
        //Card CVV
        JLabel tl3 = new JLabel("Card CVV: ");
        JTextField tf3 = new JTextField();
        //card user
        JLabel tl4 = new JLabel("Card user: ");
        JTextField tf4 = new JTextField();
        //card end date
        JLabel tl5 = new JLabel("Card End-Date: ");
        JTextField tf5 = new JTextField();
        //card file id
        JLabel tl13 = new JLabel("Card file id: ");
        JTextField tf13 = new JTextField();
        //Add Card buttons
        JButton but1 = new JButton(" insert Card");
        JButton but1_1 = new JButton("Add Card");

        //delete card
        //Dellete Card butons
        JButton but3 = new JButton(" Delete Card");
        JButton but3_1 = new JButton(" Delete this Card");
        //card deleteframe
        JFrame f3 = new JFrame("delete Card");
        //delete card user
        JLabel tl6 = new JLabel("Card user: ");
        JTextField tf6 = new JTextField();
        //delete card number
        JLabel tl7 = new JLabel("Card number: ");
        JTextField tf7 = new JTextField();

        //Modify card
        //add card frame
        JFrame f2 = new JFrame("Modify Card");
        //Card id 
        JLabel tl8 = new JLabel("    Card id: ");
        JTextField tf8 = new JTextField();
        //Card Number
        JLabel tl9 = new JLabel("Card Number: ");
        JTextField tf9 = new JTextField();
        //Card CVV
        JLabel tl10 = new JLabel("Card CVV: ");
        JTextField tf10 = new JTextField();
        //card user
        JLabel tl11 = new JLabel("Card user: ");
        JTextField tf11 = new JTextField();
        //card end date
        JLabel tl12 = new JLabel("Card End-Date: ");
        JTextField tf12 = new JTextField();
        //Modify buttons
        JButton but2 = new JButton(" Modify Card");
        JButton but2_1 = new JButton(" Modify this Card");

        //print Cards
        //print cards buttons
        JButton but4 = new JButton("print cards");
        JFrame f4 = new JFrame("Print Card");

        but1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //frame

                f1.setDefaultCloseOperation(EXIT_ON_CLOSE);
                f1.setLayout(null);
                f1.setBounds(700, 150, 400, 400);
                f1.setVisible(true);

                //Card id 
                tl1.setBounds(0, 0, 100, 30);
                tf1.setBounds(100, 0, 150, 30);
                f1.add(tl1);
                f1.add(tf1);

                //Card Number
                tl2.setBounds(0, 50, 100, 30);
                tf2.setBounds(100, 50, 150, 30);
                f1.add(tl2);
                f1.add(tf2);

                //Card CVV
                tl3.setBounds(0, 100, 100, 30);
                tf3.setBounds(100, 100, 150, 30);
                f1.add(tl3);
                f1.add(tf3);

                //card user
                tl4.setBounds(0, 150, 100, 30);
                tf4.setBounds(100, 150, 150, 30);
                f1.add(tl4);
                f1.add(tf4);

                //card end date
                tl5.setBounds(0, 200, 100, 30);
                tf5.setBounds(100, 200, 150, 30);
                f1.add(tl5);
                f1.add(tf5);

                //cardfileid
                tl13.setBounds(0, 250, 100, 30);
                tf13.setBounds(100, 250, 150, 30);
                f1.add(tl13);
                f1.add(tf13);

                but1_1.setVisible(true);
                but1_1.setBounds(100, 300, 150, 30);
                f1.add(but1_1);

            }
        }
        );
        f.add(but1);
        but1.setVisible(true);
        but1.setBounds(0, 0, 150, 50);

        but1_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String encryptedUserName;
                Card newCard = new Card(tf1.getText(), tf2.getText(), tf5.getText(), tf4.getText(), tf3.getText());
                Card EcryptedCard1 = newCard.encrypteCardData(tf4.getText(), newCard);
                System.out.println("newcard: " + EcryptedCard1);
                EcryptedCard1.addcard(tf13.getText(), tf4.getText(), tf2.getText(), EcryptedCard1);
                tf1.setText(null);
                tf2.setText(null);
                tf3.setText(null);
                tf4.setText(null);
                tf5.setText(null);
                tf13.setText(null);
                f1.setVisible(false);
            }
        }
        );

        but3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //frame

                f3.setDefaultCloseOperation(EXIT_ON_CLOSE);
                f3.setLayout(null);
                f3.setBounds(700, 150, 400, 400);
                f3.setVisible(true);

                //card user
                tl6.setBounds(0, 0, 150, 30);
                tf6.setBounds(100, 0, 150, 30);
                f3.add(tl6);
                f3.add(tf6);

                //Card Number 
                tl7.setBounds(0, 50, 100, 30);
                tf7.setBounds(100, 50, 150, 30);
                f3.add(tl7);
                f3.add(tf7);

                but3_1.setVisible(true);
                but3_1.setBounds(100, 100, 150, 30);
                f3.add(but3_1);

            }
        }
        );
        but3_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                //Card newCard = new Card(tf1.getText(), tf2.getText(), tf5.getText(), tf4.getText(), tf3.getText());
                //  newCard.addcard(newCard);
                //int result = deleteCard(tf7.getText(), tf6.getText());
                int result = delete(tf7.getText(), tf6.getText());
                if (result == 1) {
                    JOptionPane.showMessageDialog(f, "Successfully deleted.", "delete progress", JOptionPane.WARNING_MESSAGE);
                }
                tf6.setText(null);
                tf7.setText(null);
                f3.setVisible(false);
            }
        }
        );
        f.add(but3);
        but3.setVisible(true);
        but3.setBounds(200, 0, 150, 50);

        but2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //frame

                f2.setDefaultCloseOperation(EXIT_ON_CLOSE);
                f2.setLayout(null);
                f2.setBounds(700, 150, 400, 400);
                f2.setVisible(true);

                //Card id 
                tl8.setBounds(0, 0, 100, 30);
                tf8.setBounds(100, 0, 150, 30);
                f2.add(tl8);
                f2.add(tf8);

                //Card Number
                tl9.setBounds(0, 50, 100, 30);
                tf9.setBounds(100, 50, 150, 30);
                f2.add(tl9);
                f2.add(tf9);

                //Card CVV
                tl10.setBounds(0, 100, 100, 30);
                tf10.setBounds(100, 100, 150, 30);
                f2.add(tl10);
                f2.add(tf10);

                //card user
                tl11.setBounds(0, 150, 100, 30);
                tf11.setBounds(100, 150, 150, 30);
                f2.add(tl11);
                f2.add(tf11);

                //card end date
                tl12.setBounds(0, 200, 100, 30);
                tf12.setBounds(100, 200, 150, 30);
                f2.add(tl12);
                f2.add(tf12);

                but2_1.setVisible(true);
                but2_1.setBounds(100, 250, 150, 30);
                f2.add(but2_1);

                JLabel help1 = new JLabel("Help1: Card Number : 16 Numbers");
                help1.setBounds(0, 290, 200, 30);
                f1.add(help1);
                JLabel help2 = new JLabel("Help2: Card CVV: 3 Numbers");
                help2.setBounds(0, 320, 200, 30);
                f1.add(help2);

            }
        }
        );

        but2_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                //Card newCard = new Card(tf1.getText(), tf2.getText(), tf5.getText(), tf4.getText(), tf3.getText());
                //  newCard.addcard(newCard);
                tf6.setText(null);
                tf7.setText(null);
                f2.setVisible(false);
            }
        }
        );
        f.add(but2);
        but2.setVisible(true);
        but2.setBounds(400, 0, 150, 50);

        but4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //frame
                f4.setDefaultCloseOperation(EXIT_ON_CLOSE);
                f4.setLayout(null);
                f4.setBounds(700, 150, 400, 400);
                f4.setVisible(true);
                String s = "aaaaaaa";
                JLabel l = new JLabel(s);
                l.setVisible(true);
                l.setBounds(0, 0, 150, 50);
                f4.add(l);

            }
        }
        );
        f.add(but4);
        but4.setVisible(true);
        but4.setBounds(600, 0, 150, 50);

        exit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // System.exit(0);
                Integrity_mech integ = new Integrity_mech();
                integ.Integrity_mech_program_end(tf4.getText(), tf13.getText());
                f.setVisible(false);
            }
        }
        );
        f.add(exit);
        exit.setVisible(true);
        exit.setBounds(582, 710, 200, 50);
    }

}
