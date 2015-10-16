package model;

import java.io.*;
import java.time.LocalDateTime;
import java.util.*;

public class User implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 9019686643378707326L;
	
	private String name;
	private String IPAddress;
	private List<String> friends;
	private LocalDateTime lastConnection;
	
	public User(String name, String IPAddress) {
		this.name = name;
		this.IPAddress = IPAddress;
		this.lastConnection = LocalDateTime.now();
		this.friends = new ArrayList<String>();
	}
	
	public User(String name) {
		this.name = name;
		this.IPAddress = null;
		this.lastConnection = LocalDateTime.now();
		this.friends = new ArrayList<String>();
	}
	
	public String getName() {
		return name;
	}
	
	public User setName(String name) {
		this.name = name;
		return this;
	}
	
	public String getIPAddress() {
		return IPAddress;
	}
	
	public User setIPAddress(String IPAddress) {
		this.IPAddress = IPAddress;
		return this;
	}
	
	public LocalDateTime getLastConnection() {
		return lastConnection;
	}
	
	public User updateLastConnection() {
		this.lastConnection = LocalDateTime.now();
		return this;
	}
	
	public List<String> getAllFriends() {
		return friends;
	}
	
	public User addFriend(String name) {
		if (!friends.contains(name)) {
			friends.add(name);
		}
		return this;
	}
	
	public User removeFriend(String name) {
		if (friends.contains(name)) {
			friends.remove(name);
		}
		return this;
	}
	
	private void writeObject(ObjectOutputStream out) throws IOException {
		out.writeUTF(name);
		out.writeUTF(IPAddress);
		out.writeObject(lastConnection);
		out.writeObject(friends);
	}
	
	@SuppressWarnings("unchecked")
	private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
		name = in.readUTF();
		IPAddress = in.readUTF();
		lastConnection = (LocalDateTime)in.readObject();
		friends = (List<String>)in.readObject();
	}
}
