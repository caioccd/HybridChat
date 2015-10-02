package model;

import java.util.List;

public interface IUserDAO {
	   public List<User> getAllUsers();
	   public User getUser(String name);
	   public void updateUser(User user);
	   public void addUser(User user);
	   public void deleteUser(String name);
}
