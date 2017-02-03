package space.jacobschlecht.www.server;

import space.jacobschlecht.www.server.commands.BaseCommand;

public class Wonderlust {
	
	public static ServerThread server;

	public static void main(String[] args) {
		BaseCommand.initializeCommands();
		server = new ServerThread(new World("Default"));
		server.start();
	}
}
