package controller;

import java.io.*;
import java.net.*;

public class ConnectionListener implements Runnable {

    private ServerSocket serverSocket;
    private IConnectionHandler connectionHandler;
    private boolean keepListening;
    private ObjectInputStream input;

    public ConnectionListener(int port, IConnectionHandler connectionHandler) {
        try {
            this.serverSocket = new ServerSocket(port);
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

                socket.setKeepAlive(getKeepAliveFromClientStream(socket));//bind client´s keepAlive to its socket

                connectionHandler.handleConnection(socket);

                socket.close();
            }
        } catch (IOException | ClassNotFoundException e) {
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

    //get from client a keepAlive message 
    public boolean getKeepAliveFromClientStream(Socket socket) throws ClassNotFoundException, IOException {
        ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
        return (boolean) input.readObject();
    }
}
