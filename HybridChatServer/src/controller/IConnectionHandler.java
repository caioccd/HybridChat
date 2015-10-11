package controller;

import java.io.*;
import java.net.Socket;

public interface IConnectionHandler {

    /*percebi que para além dos argumentos que foram antes passados 
     faltava a porta e o nome do usuario, acabei trocando tudo por um socket para diminuir o numero de argumentos no metodo
     */
    public void handleConnection(Socket socket);

}
