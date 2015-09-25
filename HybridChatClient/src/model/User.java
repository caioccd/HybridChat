package model;

public class User {
	private String name;
	private int IPAddress;
	private boolean isActive;

	public User() {
		super();
	}
	public User(String name, int IPAddress, boolean isActive) {
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
	public int getIPAddress() {
		return IPAddress;
	}
	public void setIPAddress(int IPAddress) {
		this.IPAddress = IPAddress;
	}
	public boolean isActive() {
		return isActive;
	}
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
}
