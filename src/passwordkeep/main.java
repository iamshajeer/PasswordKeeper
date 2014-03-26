/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package passwordkeep;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
/*
 *
 * @author Shajeer
 */

public class main extends JFrame {

    JTextField t1;
    JPasswordField t2;

    public main() {
        setLayout(new FlowLayout(40, 35, 20));
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("a.png")));
        t1 = new JTextField(25);
        t1.setText("Username");
        t1.setFocusable(true);
        t1.selectAll();
        t2 = new JPasswordField(25);
        t1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                t1.selectAll();
            }
        });
        JButton b = new JButton("Get in !");
        add(t1);
        add(t2);
        add(b);
        b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                DbConnect c;
                try {
                    c = new DbConnect();


                    if (c.validlog(t1.getText(), new String(t2.getPassword()))) {
                        System.out.println(c.validlog(t1.getText(), new String(t2.getPassword())));
                        setVisible(false);
                        new MainFrame();
                    }

                } catch (Exception ex) {
                }
            }
        });


        setSize(350, 250);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);

        setTitle("Shaj Pa$$word keeper !");
        JMenuBar bar = new JMenuBar();
        JMenu file = new JMenu("File");

        setJMenuBar(bar);
        JMenuItem set = new JMenuItem("Change Username/Password");
        JMenuItem exit = new JMenuItem("Exit");
        file.add(set);
        file.add(exit);

        //settings listener
        set.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                new Settings();

            }
        });
        //listener ends here 

        bar.add(file);

        setVisible(true);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    public static void main(String[] args) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
        }
        new main();
    }
}
