import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;

import controller.ServerConnector;

public class Main {

	public static void main(String[] args) {
		ServerConnector serverConnection = new ServerConnector("127.0.0.1", 1234);
		
		
		Scanner keyboard = new Scanner(System.in);
		
		PrintStream output = null;
		try {
			output = new PrintStream(serverConnection.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
		 
		while (keyboard.hasNextLine()) {
			output.println(keyboard.nextLine());
		}
		
		keyboard.close();
		serverConnection.close();
	}

}
