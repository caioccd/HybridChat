package controller;

import java.io.*;
import java.net.*;

public class ConnectionListener implements Runnable {

    private ServerSocket serverSocket;
    private IConnectionHandler connectionHandler;
    private boolean keepListening;

    public ConnectionListener(int port, IConnectionHandler connectionHandler) {
        try {
            this.serverSocket = new ServerSocket(port);
            
            this.serverSocket.setSoTimeout(3600000);
            this.connectionHandler = connectionHandler;
        } catch (IOException e) {
            System.err.printf("An error ocurred while initializing a server socket on port %d.\n", port);
        }
    }

    public void run() {
        keepListening = true;
        try {
            while (keepListening) {
                Socket socket = serverSocket.accept();
                
                connectionHandler.handleConnection(socket.getInetAddress().getHostAddress(), socket.getInputStream(), socket.getOutputStream());

                socket.close();
            }
        } catch (IOException e) {
            stop();
            System.err.println("An error ocurred while accepting a connection.");
        } finally {
            try {
                serverSocket.close();
            } catch (IOException e) {
                System.err.println("An error ocurred while closing a server socket.");
            }
        }
    }

    public void stop() {
        keepListening = false;
    }
}
