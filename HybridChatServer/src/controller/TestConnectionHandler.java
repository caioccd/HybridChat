package controller;

import java.io.*;
import java.util.Scanner;

public class TestConnectionHandler implements IConnectionHandler {

	public TestConnectionHandler() {
		super();
	}
	
	public void HandleConnection(String IPAddress, InputStream inputStream, OutputStream outputStream) {		
		Scanner scanner = new Scanner(inputStream);
		
		while (scanner.hasNextLine()) {
		  System.out.printf("%s: %s\n", IPAddress, scanner.nextLine());
		}
		
		scanner.close();
	}
}
