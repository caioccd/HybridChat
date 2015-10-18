package controller;

import view.LoginWindow;
import view.FriendsWindow;

public class LaunchWindows {

    private String userName;
    private String ServerIp;
    private FriendsWindow frindsWindow;
   
    MainClientHandler mainClientHandler;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getServerIp() {
        return ServerIp;
    }

    public void setServerIp(String ServerIp) {
        this.ServerIp = ServerIp;
    }

  

    // login section
    public void launchLoginWindow(LaunchWindows launchLoginWindow,MainClientHandler mainClientHandler) {
        this.mainClientHandler=mainClientHandler;
        LoginWindow loginWindow = new LoginWindow(launchLoginWindow,mainClientHandler);
        loginWindow.setVisible(true);
        loginWindow.btnOkListerner();
        handleLoginCancelbutton(loginWindow);

    }
    public MainClientHandler getInstance(){
        return mainClientHandler;
    }

    public void setLoginInputs(String userName, String ServerIp, LoginWindow loginWindow) {
        setUserName(userName);
        setServerIp(ServerIp);
        loginWindow.setVisible(false);
        loginWindow.dispose();
        frindsWindow = new FriendsWindow();
        frindsWindow.setVisible(true);
        frindsWindow.run(frindsWindow);
        getInstance().RequestFriendsList();

    }

    public void handleLoginCancelbutton(LoginWindow loginWindow) {
        loginWindow.btnCancellListerner();
        //loginWindow.dispose();
    }

    // friends window section
    public void setTableContent(String name, boolean status) {

        frindsWindow.setTableContent(name, status);
        frindsWindow.btnAddListerner();
        frindsWindow.btnRemovelListerner();
        frindsWindow.btnExitMessangerListerner();
        frindsWindow.btnChatInvoker();
        frindsWindow.setInstance(this);
        
    }

    public void exitMessanger(FriendsWindow friendsWindow) {
        System.exit(1);
    }
    
    public void invokePeerChatWindow(String peerName, MainClientHandler mainClientHandler) {
        mainClientHandler.typeMessageAreaListerner();
        mainClientHandler.launchChatWindow(mainClientHandler);
        mainClientHandler.receiveMessageFromPeer(mainClientHandler);
        
    }

}
