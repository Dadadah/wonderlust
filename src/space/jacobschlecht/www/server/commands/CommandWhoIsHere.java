package space.jacobschlecht.www.server.commands;

import space.jacobschlecht.www.server.Player;

public class CommandWhoIsHere extends Command {

	public String execute(String[] args, Player ply) {
		if (ply.getRoom() != null) {
			return ply.getRoom().whoIsHere();
		}
		return "";
	}
	
	public String help(Player ply) {
		return "List players in your current room.";
	}
}
