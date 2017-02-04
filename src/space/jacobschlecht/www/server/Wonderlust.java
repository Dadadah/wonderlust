package space.jacobschlecht.www.server;

import space.jacobschlecht.www.server.commands.Command;

public class Wonderlust {
	
	public static ServerThread server;

	public static void main(String[] args) {
		Command.initializeCommands();
		server = new ServerThread(new World("Default"));
		server.start();
	}
}
