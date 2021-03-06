package controller;

import java.io.*;
import java.net.Socket;

public class ServerConnector {

    private Socket clientSocket;

    public ServerConnector(String IPAddress, int port) {
        try {
            clientSocket = new Socket(IPAddress, port);
            clientSocket.setSoTimeout(3600000);
        } catch (IOException e) {
            System.err.printf("An error ocurred while connecting to %s:%d.\n", IPAddress, port);
        }
    }

    public InputStream getInputStream() throws IOException {
        return clientSocket.getInputStream();
    }

    public OutputStream getOutputStream() throws IOException {
        return clientSocket.getOutputStream();
    }

    public void close() {
        try {
            clientSocket.close();
        } catch (IOException e) {
            System.err.println("An error ocurred while closing a client socket.");
        }
    }
}
