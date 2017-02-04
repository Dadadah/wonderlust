package space.jacobschlecht.www.server.commands;

import space.jacobschlecht.www.server.Player;

public class CommandWhoIsOnline extends BaseCommand {

	public String execute(String[] args, Player ply) {
		if (ply.getWorld() != null) {
			return ply.getWorld().whoIsHere();
		}
		return "";
	}
}
