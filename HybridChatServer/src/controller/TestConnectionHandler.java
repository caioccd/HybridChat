package controller;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class TestConnectionHandler implements IConnectionHandler {

	public TestConnectionHandler() {
		super();
	}
	
	public void HandleConnection(Socket socket) {		
		Scanner scanner = null;
		try {
			scanner = new Scanner(socket.getInputStream());
			while (scanner.hasNextLine()) {
			  System.out.println(scanner.nextLine());
			}
		} catch (IOException e) {
			System.err.printf("An error ocurred while accepting a connection.");
		}
		finally {
			try {
				socket.close();
			}
			catch (IOException e) {
				System.err.printf("An error ocurred while closing a server socket.");
			}
			scanner.close();
		}
	}
}
