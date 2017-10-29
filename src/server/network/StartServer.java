package server.network;

import utilities.Config;

public class StartServer {
	public static void main(String[] argv) {
		System.out.println("Starting server ...");
		new UnivServer(Config.DEFAULT_PORT);
	}
}
