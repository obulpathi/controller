package cz.tchuba.robotremote;

import java.net.Socket;

public class ConnectionManager {
	
	private static ConnectionManager instance = null;
	
	public Socket socket;
	
	private ConnectionManager(){		
	}
	
	public static ConnectionManager getInstance(){
		if (instance == null){
			instance = new ConnectionManager(); 
		}
		return instance;
	}

	public void deleteSelf() {
		instance = null;
		
	}

}
