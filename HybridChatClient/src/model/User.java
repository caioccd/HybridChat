package model;

import java.io.Serializable;

public class User implements Serializable {;

	private static final long serialVersionUID = 7578615896571122687L;
	
	public static final int IPv4_ADDRESS_SIZE = 4;
	public static final int IPv6_ADDRESS_SIZE = 16;
	
	public static final int IP_ADDRESS_SIZE = IPv4_ADDRESS_SIZE;
	
	private byte[] IPAddress;
	private boolean isActive;
	private String name;

	public User() {
		super();
	}
	public User(String name, byte[] IPAddress, boolean isActive) {
		super();
		this.name = name;
		this.IPAddress = IPAddress;
		this.isActive = isActive;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public byte[] getIPAddress() {
		return IPAddress;
	}
	public void setIPAddress(byte[] IPAddress) {
		this.IPAddress = IPAddress;
	}
	public boolean isActive() {
		return isActive;
	}
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
}
