package controller;

import java.io.*;
import java.util.Scanner;


public class ConnectionHandler implements IConnectionHandler {

	public ConnectionHandler() {
		super();
	}
	
	public void HandleConnection(String IPAddress, InputStream inputStream, OutputStream outputStream) {		
		Scanner scanner = new Scanner(inputStream);
		
		while (scanner.hasNextInt()) {
			switch (scanner.nextInt()) {
			case CMD_REGISTER_USER:
				
				break;
			}
		}
		
		scanner.close();
	}
}
