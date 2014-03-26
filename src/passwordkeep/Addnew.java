/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package passwordkeep;

import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 *
 * @author Shajeer
 */
public class Addnew extends JFrame {
 public Addnew(){
     setSize(350, 250);
     setLayout(new FlowLayout(25, 5, 15));
     JLabel l1=new JLabel("Username ::");
     
     JTextField us=new JTextField(15);
     JLabel l2=new JLabel("                             Url ::");
     JTextField ur=new JTextField(15);
   
     JLabel l3=new JLabel("Password ::");
     JPasswordField pa=new JPasswordField(15);
     l1.setLabelFor(us);
     add(l1);
     add(us);
     add(l2);
     add(ur);
     add(l3);
     add(pa);
     JButton back=new JButton("Back");
     add(back);
     setAlwaysOnTop(true);
     setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
     
     setVisible(true);
 
 }
 public static void main(String[] args){
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
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(change.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(change.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(change.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(change.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
//                new change(new JTable()).setVisible(true);
            }
        });
 new Addnew();
 }   
}
