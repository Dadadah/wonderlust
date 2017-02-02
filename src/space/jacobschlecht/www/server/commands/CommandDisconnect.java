package space.jacobschlecht.www.server.commands;

import space.jacobschlecht.www.server.Player;

public class CommandDisconnect extends BaseCommand {

	public String execute(String[] args, Player ply) {
		ply.disconnect();
		return "";
	}
	
}
