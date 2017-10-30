package client;

import java.util.Timer;

import utilities.Config;

public class StartTerminals {
	public static void main(String[] argv) {
		new UnivClient(Config.DEFAULT_HOST, Config.DEFAULT_PORT);
		Timer timer = new Timer();
		timer.schedule(new NumberOfDays(), 0, 20000);
	}
}
