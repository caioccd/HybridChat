package controller;

import java.io.*;
import java.net.Socket;
import java.net.SocketException;
import java.util.HashMap;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.User;
import model.UserDAOFile;

public class ConnectionHandler implements IConnectionHandler {
    User user;
    UserDAOFile userDaoFile;
    private final String databaseFilePath="users.txt";
    public static  HashMap<String, User> userHashMap;
    public ConnectionHandler() {
        super();
    }
  
    //working here
    public void handleConnection(Socket socket) {
        try {
            user = new User(socket.getInetAddress().getHostName(),socket.getPort(), socket.getKeepAlive());
            
            userDaoFile=new UserDAOFile(databaseFilePath);
            
            userDaoFile.addUser(user);
            
        } catch (SocketException ex) {
            Logger.getLogger(ConnectionHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
