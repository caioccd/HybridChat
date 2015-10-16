package model;

import java.io.*;
import java.util.*;

public class UserDAOFile implements IUserDAO, Serializable {
	
	private static final long serialVersionUID = 344362098123000066L;
	private String databaseFilePath;
	private HashMap<String, User> userHashMap;
	
	public UserDAOFile(String databaseFilePath) {
		this.databaseFilePath = databaseFilePath;
		
		ObjectInputStream inputStream = null;
	    try {
	    	inputStream = new ObjectInputStream(new FileInputStream(databaseFilePath));
			readObject(inputStream);
			inputStream.close();
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	private void saveUserDAOFile() {
		ObjectOutputStream outputStream = null;
	    try {
	    	outputStream = new ObjectOutputStream(new FileOutputStream(databaseFilePath));
			writeObject(outputStream);
			outputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public List<User> getAllUsers() {
		return new ArrayList<User>(userHashMap.values());
	}

	public User getUser(String name) {
		return userHashMap.get(name);
	}

	public void updateUser(User user) {
		userHashMap.put(user.getName(), user);
		saveUserDAOFile();
	}

	public void addUser(User user) {
		userHashMap.put(user.getName(), user);
		saveUserDAOFile();
	}

	public void deleteUser(String name) {
		userHashMap.remove(name);
		saveUserDAOFile();
	}

	private void writeObject(ObjectOutputStream out) throws IOException {
		out.writeInt(userHashMap.size());
		
		for(User user : userHashMap.values()) {
			out.writeObject(user);
		}
	}
	
	private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
		int usersCount = in.readInt();
		userHashMap = new HashMap<String, User>();
		for (int i = 0; i < usersCount; i++) {
			User user = (User)in.readObject();
			userHashMap.put(user.getName(), user);
		}
	}
}
