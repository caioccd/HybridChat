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
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getIPAddress() {
		return IPAddress;
	}
	
	public void setIPAddress(String IPAddress) {
		this.IPAddress = IPAddress;
	}
	
	public LocalDateTime getLastConnection() {
		return lastConnection;
	}
	
	public void updateLastConnection() {
		this.lastConnection = LocalDateTime.now();
	}
	
	public List<String> getAllFriends() {
		return friends;
	}
	
	public void addFriend(String name) {
		friends.add(name);
	}
	
	public void removeFriend(String name) {
		friends.remove(name);
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
