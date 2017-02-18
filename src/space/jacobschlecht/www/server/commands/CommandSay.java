package space.jacobschlecht.www.server.commands;

import space.jacobschlecht.www.server.Player;

public class CommandSay extends Command {

	public String execute(String[] args, Player ply) {
		StringBuilder message = new StringBuilder();
		for (int i = 1; i < args.length; i++) {
			message.append(args[i] + " ");
		}
		if (ply.getRoom() != null) {
			ply.getRoom().sendMessage(ply, message.toString());
		}
		return "";
	}
	
	public String help(Player ply) {
		return "Send a message to your current room.";
	}
	
}
