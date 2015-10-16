import util.Util;
import controller.ConnectionListener;
import controller.MainConnectionHandler;

public class Main {
	//main
	public static void main(String[] args) {
		MainConnectionHandler mainHandler = new MainConnectionHandler();
		ConnectionListener connectionListener = new ConnectionListener(Util.SERVER_PORT, mainHandler);
		connectionListener.run();
	}
}