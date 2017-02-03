package space.jacobschlecht.www.server.commands;

import java.util.HashMap;

import space.jacobschlecht.www.server.Player;

public class BaseCommand {

	private static HashMap<String, BaseCommand> commands = new HashMap<>();
	
	public String execute(String[] args, Player ply) {
		return args[0];
	}
	
	public static String executeCommand(String command, Player ply) {
		String[] args = command.split(" ");
		command = args[0];
		if (commands.containsKey(command)) {
			return commands.get(command).execute(args, ply);
		}
		return "Command not found!";
	}
	
	public static void initializeCommands() {
		commands.put("say", new CommandSay());
		commands.put("gsay", new CommandGSay());
		commands.put("disconnect", new CommandDisconnect());
		commands.put("town", new CommandTown());
	}
	
}
