package model;

import java.io.*;
import java.util.*;

public class UserDAOFile implements IUserDAO, Serializable {
	
	private static final long serialVersionUID = 344362098123000066L;
	private String databaseFilePath;
	private HashMap<String, User> userHashMap;
	
	public UserDAOFile(String databaseFilePath) {
		userHashMap = new HashMap<String, User>();
		this.databaseFilePath = databaseFilePath;
		
		ObjectInputStream inputStream = null;
	    try {
	    	File databaseFile = new File(databaseFilePath);
	    	if (databaseFile.length() > 0) {
		    	inputStream = new ObjectInputStream(new FileInputStream(databaseFile));
				readObject(inputStream);
				inputStream.close();
	    	}
	    	
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
	
	public boolean exists(String name) {
		return userHashMap.containsKey(name);
	}

	public User getUser(String name) {
		return userHashMap.get(name);
	}
	
	public List<User> getFriendsOf(String name) {
		ArrayList<User> fullFriendsList = new ArrayList<User>();
		
		if (this.exists(name)) {
			List<String> friends = this.getUser(name).getAllFriends();
			for (String friend : friends) {
				fullFriendsList.add(this.getUser(friend));
			}
		}
		
		return fullFriendsList;
	}

	public void updateUser(User user) {
		if (this.exists(user.getName())) {
			userHashMap.put(user.getName(), user);
			saveUserDAOFile();
		}
	}

	public void addUser(User user) {
		if (!this.exists(user.getName())) {
			userHashMap.put(user.getName(), user);
			saveUserDAOFile();
		}
	}

	public void deleteUser(String name) {
		if (this.exists(name)) {
			userHashMap.remove(name);
			saveUserDAOFile();
		}
	}

	private void writeObject(ObjectOutputStream out) throws IOException {
		out.writeObject(userHashMap);
	}
	
	@SuppressWarnings("unchecked")
	private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
		userHashMap = (HashMap<String, User>)in.readObject();
	}
}
