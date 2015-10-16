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
import java.time.LocalDateTime;
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

    	String userName = null;
    	String friendName = null;
        try {
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
    				
    				output.writeObject(userDao.getFriendsOf(userName));
    			break;
    			
        		case Util.ADD_FRIEND_COMMAND:
        			userName = input.readUTF();
        			friendName = input.readUTF();

        			userDao.updateUser(userDao.getUser(userName).addFriend(friendName).updateLastConnection());
        		break;
        		
        		case Util.DELETE_FRIEND_COMMAND:
        			userName = input.readUTF();
        			friendName = input.readUTF();
        			
        			userDao.updateUser(userDao.getUser(userName).removeFriend(friendName).updateLastConnection());
        		break;
        	}

        } catch (IOException e) {
			e.printStackTrace();
        }

    }

    private void setupStreams(InputStream inputStream, OutputStream outputStream) {
        try {
            output = new ObjectOutputStream(outputStream);
            output.flush();
            input = new ObjectInputStream(inputStream);
        } catch (IOException e) {
			e.printStackTrace();
        }
    }
    
   

   

}
