package view;

import controller.LaunchWindows;
import controller.MainClientHandler;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class FriendsWindow extends javax.swing.JFrame {

    public boolean isPressed = false;
    private Object[][] data = new Object[1][2];
    private String[] ColumnName = {"Name", "status"};
    private LaunchWindows launchLoginWindow;
    private final List<Boolean> isActive;
    public static int chatController = 0;

    public static boolean flagEvent = false;

    public FriendsWindow() {
        this.isActive = new ArrayList<Boolean>();
        initComponents();
        setLookAndFeel();
        framePosition();
        for (int i = 0; i < data.length; i++) {
            isActive.add(false);
        }

    }

    public void setInstance(LaunchWindows launchLoginWindow) {
        this.launchLoginWindow = launchLoginWindow;
    }

    public void run(FriendsWindow friendsWindow) {

        java.awt.EventQueue.invokeLater(() -> {
            friendsWindow.setVisible(true);
        });

    }

    public boolean checkActiveChat(int i) {
        if (this.isActive.get(i)) {
            return true;
        }
        return false;
    }

    public void enableButton(JButton btn) {

        btn.setEnabled(true);
    }

    public void setTableContent(String userName, boolean status) {
        Object[][] temp = new Object[data.length + 1][2];
        temp = new Object[data.length + 1][2];
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[i].length; j++) {
                temp[i][j] = data[i][j];
            }
        }
        temp[data.length][0] = userName;// name;
        temp[data.length][1] = status;//String.valueOf(keepAlive);
        data = temp.clone();
        this.getFiendsTable().setModel(new DefaultTableModel(data, ColumnName));
        enableButton(btnAdd);
        enableButton(btnRemove);

    }

    public void enableTextField(JTextField textField) {
        textField.setVisible(true);
        textField.setEnabled(true);
    }

    public void disEnableTextField(JTextField textField) {

        textField.setEnabled(false);
    }

    public void enableLabel(String message) {
        labelMessage.setText(message);
        labelMessage.setVisible(true);

    }

    public JTable getFiendsTable() {
        return table;
    }

    public JButton getAdd() {
        return btnAdd;
    }

    public JButton getRemove() {
        return btnRemove;
    }

    protected int confirmation(String message) {
        return JOptionPane.showConfirmDialog(null, message, "Confirm", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

    }

    public void btnAddListerner() {
        btnAdd.addActionListener((ActionEvent e) -> {

            enableTextField(textAdd);

            if (flagEvent) {
                //launchLoginWindow.addFriend(textAdd.getText().trim());
                launchLoginWindow.getInstance().addFriend(textAdd.getText().trim());

                setTableContent(textAdd.getText().trim(), true);
                textAdd.setText(null);
                flagEvent = false;

            }
            flagEvent = true;
        });

    }

    public String selectingUser() {
        int i = table.getSelectedRow();
        String friendName = null;
        if (i > -1) {
            friendName = table.getModel().getValueAt(i, 0).toString();
            for (int j = 0; j < data[i].length; j++) {

                table.getModel().setValueAt("", i, j);
                data[i][j] = "";

            }

            return friendName;
        } else {
            JOptionPane.showMessageDialog(null, "select a friend you want to remove");
        }
        return friendName;

    }

    public String selectUserToChat() {
        int i = table.getSelectedRow();
        String friendName = null;
        if (i > -1) {
            friendName = table.getModel().getValueAt(i, 0).toString();
            return friendName;
        }
        return friendName;

    }

    public void btnRemovelListerner() {
        btnRemove.addActionListener((ActionEvent e) -> {
            String friendName = selectingUser();
            if (friendName != null) {

                //launchLoginWindow.removeFriend(friendName);
                launchLoginWindow.getInstance().removeFriend(friendName);
            }

        });

    }

    public void btnExitMessangerListerner() {
        btnExitMessanger.addActionListener((ActionEvent e) -> {
            int opc = confirmation("do you want to close messenger?");
            if (opc == 0) {

                launchLoginWindow.getInstance().exitMessanger(this);

            }

        });

    }

    public void btnChatInvoker() {
        btnChat.addActionListener((ActionEvent e) -> {

            String friendName = selectUserToChat();

            if (friendName != null) {
                if (!checkActiveChat(chatController)) {
                    //isActive.add(chatController, true);
                    MainClientHandler mainClientHandler = new MainClientHandler();
                    launchLoginWindow.invokePeerChatWindow(friendName, mainClientHandler);

                } else {
                    JOptionPane.showMessageDialog(null, "check the this user previous chat window");
                }
            } else {
                JOptionPane.showMessageDialog(null, "select a friend you want to talk");
            }

        });

    }

    protected final void framePosition() {
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        btnAdd = new javax.swing.JButton();
        textAdd = new javax.swing.JTextField();
        btnRemove = new javax.swing.JButton();
        labelMessage = new javax.swing.JLabel();
        btnExitMessanger = new javax.swing.JButton();
        btnChat = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("FriendsWindow");
        setResizable(false);

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Name", "Status"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(table);
        if (table.getColumnModel().getColumnCount() > 0) {
            table.getColumnModel().getColumn(0).setResizable(false);
            table.getColumnModel().getColumn(1).setResizable(false);
        }

        btnAdd.setText("add");
        btnAdd.setToolTipText("add a friend");
        btnAdd.setEnabled(false);

        textAdd.setEnabled(false);

        btnRemove.setText("remove");
        btnRemove.setToolTipText("remove friend");
        btnRemove.setEnabled(false);

        labelMessage.setFont(new java.awt.Font("Comic Sans MS", 0, 11)); // NOI18N
        labelMessage.setForeground(new java.awt.Color(204, 0, 0));

        btnExitMessanger.setText("log out");

        btnChat.setText("chat");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnRemove)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnAdd)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(textAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnChat)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnExitMessanger)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelMessage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnAdd)
                        .addComponent(btnRemove)
                        .addComponent(textAdd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnExitMessanger)
                        .addComponent(btnChat))
                    .addComponent(labelMessage))
                .addGap(37, 37, 37))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
/*
     public static void main(String args[]) {
      
        
     java.awt.EventQueue.invokeLater(() -> {
     new FriendsWindow().setVisible(true);
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
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnChat;
    private javax.swing.JButton btnExitMessanger;
    private javax.swing.JButton btnRemove;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel labelMessage;
    private javax.swing.JTable table;
    private javax.swing.JTextField textAdd;
    // End of variables declaration//GEN-END:variables

}
