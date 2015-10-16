import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.List;

import util.Util;
import model.User;

public class MainClient {
	
	public static void printUsers(List<User> users) {
		for(User user: users) {
			System.out.println(user.getName() + " " + user.getIPAddress() + " " + user.getLastConnection());
			for(String friend: user.getAllFriends()) {
				System.out.print(friend + " ");
			}
			System.out.println();
			System.out.println();
		}
	}
	
	@SuppressWarnings("unchecked")
	public static void test() throws IOException, ClassNotFoundException {
		Socket socket = null;
		DataOutputStream output = null;
		ObjectInputStream input = null;
		List<User> users = null;
		
		//////////////////////////////////////////////////////////////////////////////////////
		
		socket = new Socket(Util.SERVER_IP, Util.SERVER_PORT);
		output = new DataOutputStream(socket.getOutputStream());
		
		output.writeInt(Util.GET_FRIENDS_LIST_COMMAND);
		output.writeUTF("caioccd");
		
		input = new ObjectInputStream(socket.getInputStream());
		
		users = (List<User>)input.readObject();
		printUsers(users);
		
		socket.close();
		
		//////////////////////////////////////////////////////////////////////////////////////

		
		
		//////////////////////////////////////////////////////////////////////////////////////
		
		socket = new Socket(Util.SERVER_IP, Util.SERVER_PORT);
		output = new DataOutputStream(socket.getOutputStream());

		output.writeInt(Util.GET_FRIENDS_LIST_COMMAND);
		output.writeUTF("antonio");
		
		input = new ObjectInputStream(socket.getInputStream());
		
		users = (List<User>)input.readObject();
		printUsers(users);
		
		socket.close();
		
		//////////////////////////////////////////////////////////////////////////////////////

		
		
		//////////////////////////////////////////////////////////////////////////////////////
		
		socket = new Socket(Util.SERVER_IP, Util.SERVER_PORT);
		output = new DataOutputStream(socket.getOutputStream());

		output.writeInt(Util.ADD_FRIEND_COMMAND);
		output.writeUTF("caioccd");
		output.writeUTF("antonio");
		
		socket.close();
		
		//////////////////////////////////////////////////////////////////////////////////////

		
		
		//////////////////////////////////////////////////////////////////////////////////////
		
		socket = new Socket(Util.SERVER_IP, Util.SERVER_PORT);
		output = new DataOutputStream(socket.getOutputStream());

		output.writeInt(Util.GET_FRIENDS_LIST_COMMAND);
		output.writeUTF("caioccd");
		
		input = new ObjectInputStream(socket.getInputStream());
		
		users = (List<User>)input.readObject();
		printUsers(users);
		
		socket.close();
	}

	public static void main(String[] args) throws IOException {
		try {
			test();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
