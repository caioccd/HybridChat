package view;

import controller.MainClientHandler;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

public class ChatWindow extends javax.swing.JFrame {

    protected boolean isPressed = false;
    private MainClientHandler mainClientHandler;

    public ChatWindow() {
        initComponents();
        framePosition();
        setLookAndFeel();

        disableDisplayArea();

    }

    public void launchChatWindow(MainClientHandler mainClientHandler) {
        this.mainClientHandler = mainClientHandler;
        java.awt.EventQueue.invokeLater(() -> {
            mainClientHandler.setVisible(true);
        });

    }

    protected int confirmation(String message) {
        return JOptionPane.showConfirmDialog(null, message, "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

    }

    protected final void framePosition() {
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
    }

    protected void disableDisplayArea() {
        displayMessageArea.setEditable(false);

    }

    public JTextArea getTypeMessageArea() {
        return typeMessageArea;
    }

    public JTextArea getDisplayMessageArea() {
        return displayMessageArea;
    }

    public void displayMessageFromPeer(String message) {

        getDisplayMessageArea().setText(displayMessageArea.getText() + "\n" + message);

    }

    public boolean typeMessageAreaListerner() {

        typeMessageArea.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent keyEvent) {
                int key = keyEvent.getKeyCode();
                if (key == KeyEvent.VK_ENTER && (!typeMessageArea.getText().equals(null))) {

                    displayMessageArea.setText(typeMessageArea.getText());
                    mainClientHandler.sendMessageToPeer(typeMessageArea.getText());

                }
            }
        });

        return isPressed;
    }

    public boolean displayMessageAreaListerner() {

        return isPressed;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        typeMessageArea = new javax.swing.JTextArea();
        jSeparator1 = new javax.swing.JSeparator();
        jScrollPane2 = new javax.swing.JScrollPane();
        displayMessageArea = new javax.swing.JTextArea();
        jSeparator2 = new javax.swing.JSeparator();
        btnLogout = new javax.swing.JButton();
        jSeparator3 = new javax.swing.JSeparator();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("ChatWindow");
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        typeMessageArea.setColumns(20);
        typeMessageArea.setRows(5);
        jScrollPane1.setViewportView(typeMessageArea);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 226, 430, 50));
        getContentPane().add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 188, 430, -1));

        displayMessageArea.setBackground(new java.awt.Color(204, 255, 255));
        displayMessageArea.setColumns(20);
        displayMessageArea.setRows(5);
        jScrollPane2.setViewportView(displayMessageArea);

        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 430, 180));
        getContentPane().add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 210, 430, 10));

        btnLogout.setText("logout");
        btnLogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLogoutActionPerformed(evt);
            }
        });
        getContentPane().add(btnLogout, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 280, 80, -1));
        getContentPane().add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 310, 430, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogoutActionPerformed
        int response = confirmation("do you want to close this window?");
        if (response == 0) {

            mainClientHandler.closePeerWindow(this);
        }
    }//GEN-LAST:event_btnLogoutActionPerformed
    /*
     public static void main(String args[]) {

     java.awt.EventQueue.invokeLater(() -> {
     new ChatWindow().setVisible(true);
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
    private javax.swing.JButton btnLogout;
    private javax.swing.JTextArea displayMessageArea;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JTextArea typeMessageArea;
    // End of variables declaration//GEN-END:variables
}
