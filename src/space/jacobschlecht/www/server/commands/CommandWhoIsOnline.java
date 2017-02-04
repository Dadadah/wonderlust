package space.jacobschlecht.www.server.commands;

import space.jacobschlecht.www.server.Player;

public class CommandWhoIsOnline extends Command {

	public String execute(String[] args, Player ply) {
		if (ply.getWorld() != null) {
			return ply.getWorld().whoIsHere();
		}
		return "";
	}
	
	public String help(Player ply) {
		return "Lists players currently online.";
	}
}
