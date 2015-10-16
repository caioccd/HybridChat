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

    @Override
    public void handleConnection(String IPAddress, InputStream inputStream, OutputStream outputStream) {
       
    }
  
   
}
