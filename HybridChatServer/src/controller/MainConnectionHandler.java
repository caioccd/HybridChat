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

        try {
            User user = new User(input.readUTF(), IPAddress, true);
            userDao.addUser(user);

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
            System.out.println(" check input or output stream " + ex.getLocalizedMessage());
        }
    }

    public List<User> myFriends(String name) {
        return userDao.getFriendsOf(name);

    }

    public User getUser(String name) {
        return userDao.getUser(name);
    }

    public void updateUser(User user) {
        userDao.updateUser(user);
    }

    public void addUser(User user) {
        userDao.addUser(user);
    }

    public void deleteUser(String name) {
        userDao.deleteUser(name);
    }

}
