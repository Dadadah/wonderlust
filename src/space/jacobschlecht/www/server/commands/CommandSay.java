package space.jacobschlecht.www.server.commands;

import space.jacobschlecht.www.server.Player;

public class CommandSay extends BaseCommand {

	public String execute(String[] args, Player ply) {
		StringBuilder message = new StringBuilder();
		for (int i = 1; i < args.length; i++) {
			message.append(args[i] + " ");
		}
		ply.curRoom.sendMessage(ply, message.toString());
		return "";
	}
	
}
