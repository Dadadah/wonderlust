package space.jacobschlecht.www.server.commands;

import space.jacobschlecht.www.server.Player;

public class CommandDisconnect extends Command {

	public String execute(String[] args, Player ply) {
		ply.disconnect();
		return "";
	}
	
	public String help(Player ply) {
		return "Disconnect from the server and save your character.";
	}
	
}
