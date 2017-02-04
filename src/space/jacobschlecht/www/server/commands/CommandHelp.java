package space.jacobschlecht.www.server.commands;

import java.util.Map;

import space.jacobschlecht.www.server.Player;

public class CommandHelp extends Command {

	@Override
	public String execute(String[] args, Player ply) {
		for (Map.Entry<String,Command> entry : Command.commands.entrySet()) {
			ply.sendMessage(entry.getKey() + ": " + entry.getValue().help(ply));
		}
		return "";
	}

	@Override
	public String help(Player ply) {
		return "Lists this prompt.";
	}

}
