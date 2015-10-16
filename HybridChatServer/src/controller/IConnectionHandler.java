package controller;

import java.io.*;

public interface IConnectionHandler {
    public void handleConnection(String IPAddress, InputStream inputStream, OutputStream outputStream);

}
