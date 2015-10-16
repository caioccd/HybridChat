/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.User;
import model.UserDAOFile;
import util.Util;

public class MainConnectionHandler implements IConnectionHandler {

    private UserDAOFile userDao;
    private ObjectInputStream input;
    private ObjectOutputStream output;

    public MainConnectionHandler() {
        userDao = new UserDAOFile(Util.daoFilePath);
    }

    @Override
    public void handleConnection(String IPAddress, InputStream inputStream, OutputStream outputStream) {
        setupStreams(inputStream, outputStream);

    	User user = null;
    	String userName = null;
    	String friendName = null;
        try {
        	int command = input.readInt();
        	switch (command) {
    			case Util.GET_FRIENDS_LIST_COMMAND:
    				userName = input.readUTF();
    				
    				if (!userDao.exists(userName)) {
        	            userDao.addUser(new User(userName, IPAddress, true));
    				}
    				
    				output.writeObject(userDao.getFriendsOf(userName));
    			break;
    			
        		case Util.ADD_FRIEND_COMMAND:
        			userName = input.readUTF();
        			friendName = input.readUTF();
        			
        			user = userDao.getUser(userName);
        			user.addFriend(friendName);
        			
        			userDao.updateUser(user);
        		break;
        		
        		case Util.DELETE_FRIEND_COMMAND:
        			userName = input.readUTF();
        			friendName = input.readUTF();
        			
        			user = userDao.getUser(userName);
        			user.removeFriend(friendName);
        			
        			userDao.updateUser(user);
        		break;
        	}

        } catch (IOException ex) {
            System.out.println("input feach info error" + ex.getMessage());
        }

    }

    private void setupStreams(InputStream inputStream, OutputStream outputStream) {
        try {
            output = new ObjectOutputStream(outputStream);
            output.flush();
            input = new ObjectInputStream(inputStream);
        } catch (IOException ex) {
            System.out.println("Check input or output stream " + ex.getLocalizedMessage());
        }
    }
    
   

   

}
