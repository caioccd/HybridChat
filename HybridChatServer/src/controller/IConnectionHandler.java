package controller;

import java.io.*;

public interface IConnectionHandler {
	
	public void HandleConnection(String IPAddress, InputStream inputStream, OutputStream outputStream);

}
