import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

import controller.ServerConnector;
import util.Util;

public class Main {
	
	public static void test() throws IOException {
		ServerConnector serverConnection = new ServerConnector(Util.SERVER_IP, Util.SERVER_PORT);
		
		ObjectOutputStream output = new ObjectOutputStream(serverConnection.getOutputStream());
		
		output.writeInt(Util.GET_FRIENDS_LIST_COMMAND);
		output.writeUTF("caioccd");

		output.writeInt(Util.GET_FRIENDS_LIST_COMMAND);
		output.writeUTF("antonio");

		output.writeInt(Util.ADD_FRIEND_COMMAND);
		output.writeUTF("caio");
		output.writeUTF("antonio");
		
		
		/*Scanner keyboard = new Scanner(System.in);
		
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
		serverConnection.close();*/
	}

	public static void main(String[] args) {
		test();
	}

}
