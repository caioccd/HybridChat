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
		String hostAddress = socket.getInetAddress().getHostAddress();
		try {
			scanner = new Scanner(socket.getInputStream());
			while (scanner.hasNextLine()) {
			  System.out.printf("%s: %s\n", hostAddress, scanner.nextLine());
			}
		} catch (IOException e) {
			System.err.println("An error ocurred while accepting a connection.");
		}
		finally {
			try {
				socket.close();
			}
			catch (IOException e) {
				System.err.println("An error ocurred while closing a server socket.");
			}
			scanner.close();
		}
	}
}
