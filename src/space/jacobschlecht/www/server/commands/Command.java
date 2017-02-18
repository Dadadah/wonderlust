package space.jacobschlecht.www.server.commands;

import java.util.HashMap;
import space.jacobschlecht.www.server.Player;

public abstract class Command {

	public abstract String execute(String[] args, Player ply);

	public abstract String help(Player ply);

	protected static HashMap<String, Command> commands = new HashMap<>();

	public static String executeCommand(String command, Player ply) {
		String[] args = command.split(" ");
		command = args[0];
		if (commands.containsKey(command)) {
			return commands.get(command).execute(args, ply);
		}
		return "Command not found!";
	}

	public static void initializeCommands() {
		commands.put("help", new CommandHelp());
		commands.put("?", new CommandHelp());
		commands.put("say", new CommandSay());
		commands.put("gsay", new CommandGSay());
		commands.put("disconnect", new CommandDisconnect());
		commands.put("town", new CommandTown());
		commands.put("whoishere", new CommandWhoIsHere());
		commands.put("whoisonline", new CommandWhoIsOnline());
	}

}
