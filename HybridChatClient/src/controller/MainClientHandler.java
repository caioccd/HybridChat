package controller;

import view.ChatWindow;
import view.FriendsWindow;

public class MainClientHandler extends ChatWindow {

    public static LaunchWindows launchLoginWindow;
    public static MainClientHandler mainClientHandler;
    private final String useLogin;
    private final String serString;

    public MainClientHandler() {
        useLogin = launchLoginWindow.getUserName();
        serString = launchLoginWindow.getServerIp();
    }

    // taltk to your peer 
    public void sendMessageToPeer(String message) {

        System.out.println(useLogin + " say:" + message);

    }

    // get message from  peer 
    public void receiveMessageFromPeer(MainClientHandler mainClientHandler) {

        String receivedMessage = "";

        mainClientHandler.displayMessageFromPeer(receivedMessage);

    }

    // add new friend and notify the server
    public void addFriend(String friendName) {

        System.out.println(friendName);

    }
//  remove a friend and notify the server

    public void removeFriend(String friendName) {
        
        System.out.println(friendName);
    }

    public void closePeerWindow(ChatWindow chatWindow) {
        chatWindow.dispose();
    }

    // notify the server that you got off 
    public void exitMessanger(FriendsWindow friendsWindow) {

        friendsWindow.dispose();
    }

    // request your friends List on server and fill the table
    public void RequestFriendsList() {

        launchLoginWindow.setTableContent("caio", true);//friends name and status from server
    }

    
    public static void main(String[] args) {

        launchLoginWindow = new LaunchWindows();
        MainClientHandler mainClientHandler = new MainClientHandler();
        launchLoginWindow.launchLoginWindow(launchLoginWindow, mainClientHandler);

    }

}
