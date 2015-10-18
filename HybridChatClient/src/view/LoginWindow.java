package view;

import controller.LaunchWindows;
import controller.MainClientHandler;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import javax.swing.JOptionPane;

public class LoginWindow extends javax.swing.JFrame {

    private boolean isPressed = false;
    private LaunchWindows launchLoginWindow;
    private MainClientHandler mainClientHandler;
    

    public LoginWindow(LaunchWindows launchLoginWindow,MainClientHandler mainClientHandler) {
        this.launchLoginWindow = launchLoginWindow;
        this.mainClientHandler=mainClientHandler;
        initComponents();
        setLookAndFeel();
        framePosition();
    }
 protected int confirmation(String message) {
     return JOptionPane.showConfirmDialog(null,  message, "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

   }
    protected void framePosition() {
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 4 - this.getSize().height / 4);
    }

    public void btnOkListerner() {

        btnOk.addActionListener((ActionEvent e) -> {
            if (!textUserName.getText().equals("") && !(textIPserver.getText().equals(""))) {

               launchLoginWindow.setLoginInputs(textUserName.getText(), textIPserver.getText(), this);

            }else{
                JOptionPane.showMessageDialog(null, "type user name and server ip address");
            }

        });

    }

    public void btnCancellListerner() {
        btnCancel.addActionListener((ActionEvent e) -> {
            textUserName.setText(null);
            textIPserver.setText(null);
            launchLoginWindow.handleLoginCancelbutton(this);
          
        });
        
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        textUserName = new javax.swing.JTextField();
        btnOk = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();
        textIPserver = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("LoginWindow");
        setAutoRequestFocus(false);
        setResizable(false);
        setSize(new java.awt.Dimension(0, 0));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setText("Login");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 10, 50, -1));

        jLabel2.setText("User name");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 70, 20));

        textUserName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textUserNameActionPerformed(evt);
            }
        });
        getContentPane().add(textUserName, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 30, 160, -1));

        btnOk.setText("ok");
        getContentPane().add(btnOk, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 30, -1, -1));

        btnCancel.setText("cancel");
        getContentPane().add(btnCancel, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 30, 80, -1));
        getContentPane().add(textIPserver, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 60, 160, -1));

        jLabel3.setText("Server IP");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 60, -1));

        jSeparator1.setForeground(new java.awt.Color(0, 102, 102));
        getContentPane().add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 100, 350, 10));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void textUserNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textUserNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textUserNameActionPerformed

    /*
     public static void main(String args[]) {
      
     java.awt.EventQueue.invokeLater(() -> {
     new LoginWindow().setVisible(true);
     });
     }
     */
    protected void setLookAndFeel() {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            System.out.println("lookAndFeel error" + ex.getLocalizedMessage());
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnOk;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextField textIPserver;
    private javax.swing.JTextField textUserName;
    // End of variables declaration//GEN-END:variables
}
