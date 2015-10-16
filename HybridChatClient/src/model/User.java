package model;

import java.io.*;
import java.util.*;

public class User implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 9019686643378707326L;
	
	private String name;
	private String IPAddress;
	private boolean isActive;
	private List<String> friends;
	
	public User(String name, String IPAddress, boolean isActive) {
		this.name = name;
		this.IPAddress = IPAddress;
		this.isActive = isActive;
		this.friends = new ArrayList<String>();
	}
	
	public User(String name) {
		this.name = name;
		this.IPAddress = null;
		this.isActive = false;
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
	
	public boolean isActive() {
		return isActive;
	}
	
	public void setActive(boolean isActive) {
		this.isActive = isActive;
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
		out.writeBoolean(isActive);
		out.writeObject(friends);
	}
	
	@SuppressWarnings("unchecked")
	private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
		name = in.readUTF();
		IPAddress = in.readUTF();
		isActive = in.readBoolean();
		friends = (List<String>)in.readObject();
	}
}
