package space.jacobschlecht.www.server;

import space.jacobschlecht.www.server.commands.BaseCommand;

public class Wonderlust {
	
	public static ServerThread server;

	public static void main(String[] args) {
		BaseCommand.initializeCommands();
		World world = new World("Default");
		server = new ServerThread(world);
		server.start();
	}
}
