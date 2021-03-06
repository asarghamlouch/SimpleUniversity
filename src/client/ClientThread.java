package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

import org.apache.log4j.Logger;

import utilities.Trace;

public class ClientThread extends Thread{
	private Socket         socket   = null;
	private UnivClient      client   = null;
	private BufferedReader streamIn = null;
	private boolean done = false;
	private Logger logger = Trace.getInstance().getLogger(this);
	
	public ClientThread(UnivClient client, Socket socket) {  
		this.client = client;
		this.socket = socket;
		this.open();  
		this.start();
	}
	
	public void open () {
		try {  
			streamIn  = new BufferedReader(new InputStreamReader(socket.getInputStream()));
	    } catch(IOException ioe) {  
	    	logger.info(String.format ("Error getting input stream"));
	    	String message = String.format("Exception thrown : %s \n", ioe.getMessage());
			logger.info(String.format ("Class: %-12s: %s",this.getClass().getSimpleName(), message));
			client.stop();
	    }
	}
	
	public void close () {
		done = true;
		try {  
			if (streamIn != null) streamIn.close();
			if (socket != null) socket.close();
			this.socket = null;
			this.streamIn = null;
		} catch(IOException ioe) { 
			ioe.printStackTrace();
	   }	
	}
	
	public void run() {
		while (!done) { 
			try {  
				client.handle(streamIn.readLine());
			} catch(IOException ioe) {  
				ioe.printStackTrace();
	    }}
	}	
}
