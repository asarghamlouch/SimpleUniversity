package client;

import utilities.Config;

public class StartTerminals {
	public static void main(String[] argv) {
		new UnivClient(Config.DEFAULT_HOST, Config.DEFAULT_PORT);
	}
}
