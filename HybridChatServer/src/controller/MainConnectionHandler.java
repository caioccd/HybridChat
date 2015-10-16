/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.*;
import java.util.List;

import model.*;
import util.Util;

public class MainConnectionHandler implements IConnectionHandler {

    private UserDAOFile userDao;

    public MainConnectionHandler() {
        userDao = new UserDAOFile(Util.daoFilePath);
    }

    public void handleConnection(String IPAddress, InputStream inputStream, OutputStream outputStream) {

    	String userName = null;
    	String friendName = null;
        try {
        	DataInputStream input = new DataInputStream(inputStream);
        	
         	int command = input.readInt();
        	switch (command) {
    			case Util.GET_FRIENDS_LIST_COMMAND:
    				userName = input.readUTF();
    				
    				if (userDao.exists(userName)) {
    					userDao.getUser(userName).updateLastConnection();
    				}
    				else {
        	            userDao.addUser(new User(userName, IPAddress));
    				}

    	        	ObjectOutputStream output = new ObjectOutputStream(outputStream);
    	        	List<User> users = userDao.getFriendsOf(userName);
    	        	output.writeObject(users);
    			break;
    			
        		case Util.ADD_FRIEND_COMMAND:
        			userName = input.readUTF();
        			friendName = input.readUTF();

        			if (userDao.exists(userName) && userDao.exists(friendName)) {
        				userDao.updateUser(userDao.getUser(userName).addFriend(friendName).updateLastConnection());
        				userDao.updateUser(userDao.getUser(friendName).addFriend(userName));
        			}
        		break;
        		
        		case Util.DELETE_FRIEND_COMMAND:
        			userName = input.readUTF();
        			friendName = input.readUTF();
        			
        			if (userDao.exists(userName) && userDao.exists(friendName)) {
        				userDao.updateUser(userDao.getUser(userName).removeFriend(friendName).updateLastConnection());
        				userDao.updateUser(userDao.getUser(friendName).removeFriend(userName));
        			}
        		break;
        	}

        } catch (IOException e) {
			e.printStackTrace();
        }

    } 

}
