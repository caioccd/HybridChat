import controller.TestConnectionHandler;
import controller.ConnectionListener;

public class Main {
	
	public static void main(String[] args) {
		TestConnectionHandler testHandler = new TestConnectionHandler();
		ConnectionListener connectionListener = new ConnectionListener(1234, testHandler);
		connectionListener.run();
	}
}